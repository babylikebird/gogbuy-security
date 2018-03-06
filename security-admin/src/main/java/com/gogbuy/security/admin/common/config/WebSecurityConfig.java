package com.gogbuy.security.admin.common.config;

import com.gogbuy.security.admin.modules.security.authentication.*;
import com.gogbuy.security.admin.modules.security.filter.GogUsernamePasswordAuthenticationFilter;
import com.gogbuy.security.admin.modules.security.intercept.GogFilterInvocationSecurityMetadataSource;
import com.gogbuy.security.admin.modules.security.intercept.GogSecurityInterceptor;
import com.gogbuy.security.admin.modules.security.config.TokenSettings;
import com.gogbuy.security.admin.modules.security.oauth.AuthTokenAuthenticationProcessingFilter;
import com.gogbuy.security.admin.modules.security.authentication.matcher.AuthenticationRequestMatcher;
import com.gogbuy.security.admin.modules.security.oauth.DefaultAuthenticationProcessingFilter;
import com.gogbuy.security.admin.modules.security.oauth.GogAuthenticationFailureHandler;
import com.gogbuy.security.admin.modules.security.oauth.OauthAuthenticationSuccessHandler;
import com.gogbuy.security.admin.modules.security.oauth.redis.RedisTokenStore;
import com.gogbuy.security.admin.modules.security.userdetails.UserDetailsServiceImpl;
import com.gogbuy.security.admin.modules.security.voter.UrlMatchVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:22
 * ProjectName:gogbuy-security
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";

    @Autowired
    private TokenSettings tokenSettings;
    @Autowired
    AuthenticationRequestMatcher requestMatcher;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Bean
    public RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public UserDetailsServiceImpl userDetailsServiceImpl() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Autowired
    private GogFilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 其他地址的访问均需验证权限（需要登录）
                .anyRequest().authenticated()
                .and()
                // 添加验证码验证
                .addFilterBefore(gogUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(defaultAuthenticationProcessingFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authTokenAuthenticationProcessingFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(filterSecurityInterceptor(),FilterSecurityInterceptor.class)
                .exceptionHandling()
                .authenticationEntryPoint(new GogLoginUrlAuthenticationEntryPoint())//自定义没等来返回
                .accessDeniedHandler(new GogAccessDeniedHandler())//自定义权限不够失败返回
                .and()
                .formLogin()
                // 登陆处理路径
                .loginProcessingUrl("/login").permitAll().and()
                // 成功退出登录后的返回退出成功
                .logout().logoutSuccessHandler(new GogLogoutSuccessHandler())
                .and()
                // 关闭csrf
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略文件
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html",
                "/doc.html",
                "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(new StandardPasswordEncoder());
    }
    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception{
        GogSecurityInterceptor securityInterceptor = new GogSecurityInterceptor(securityMetadataSource,accessDecisionManager(),authenticationManagerBean());
        return securityInterceptor;
    }

    //决策器
    @Bean
    public AccessDecisionManager accessDecisionManager(){
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new UrlMatchVoter());
        return new AffirmativeBased(decisionVoters);
    }

    /**
     * <p>注意：必须要有默认的过滤器，通过access_token获取</p>
     * @return
     */
    public DefaultAuthenticationProcessingFilter defaultAuthenticationProcessingFilter(){
        AuthenticationFailureHandler failureHandler = new GogAuthenticationFailureHandler();
        return new DefaultAuthenticationProcessingFilter(new AntPathRequestMatcher("/**"),failureHandler,redisTokenStore());
    }

    @Bean
    public GogUsernamePasswordAuthenticationFilter gogUsernamePasswordAuthenticationFilter() throws Exception {
        GogUsernamePasswordAuthenticationFilter myFilter = new GogUsernamePasswordAuthenticationFilter();
        myFilter.setAuthenticationManager(authenticationManagerBean());
        myFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        myFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return myFilter;
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new OauthAuthenticationSuccessHandler(tokenSettings,redisTokenStore());
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new GogUrlAuthenticationFailureHandler();
    }
    @Bean
    protected AuthTokenAuthenticationProcessingFilter authTokenAuthenticationProcessingFilter() throws Exception{
        AuthenticationFailureHandler failureHandler = new GogAuthenticationFailureHandler();
        AuthTokenAuthenticationProcessingFilter filter = new AuthTokenAuthenticationProcessingFilter(requestMatcher,failureHandler,redisTokenStore());
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
