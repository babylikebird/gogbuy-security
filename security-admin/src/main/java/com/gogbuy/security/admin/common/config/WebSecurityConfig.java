package com.gogbuy.security.admin.common.config;

import com.gogbuy.security.admin.modules.security.authentication.*;
import com.gogbuy.security.admin.modules.security.filter.GogUsernamePasswordAuthenticationFilter;
import com.gogbuy.security.admin.modules.security.intercept.GogFilterInvocationSecurityMetadataSource;
import com.gogbuy.security.admin.modules.security.intercept.GogSecurityInterceptor;
import com.gogbuy.security.admin.modules.security.userdetails.UserDetailsServiceImpl;
import com.gogbuy.security.admin.modules.security.voter.UrlMatchVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:22
 * ProjectName:gogbuy-security
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
        http.authorizeRequests().anyRequest().permitAll()
                // 其他地址的访问均需验证权限（需要登录）
                .anyRequest().authenticated()
                .and()
                // 添加验证码验证
                .addFilterAt(gogUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
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
                .csrf().disable();
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
    //资源拦截
//    @Bean("filterInvocationSecurityMetadataSource")
//    public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(){
//        GogFilterInvocationSecurityMetadataSource securityMetadataSource = new GogFilterInvocationSecurityMetadataSource();
//        return securityMetadataSource;
//    }
    //决策器
    @Bean
    public AccessDecisionManager accessDecisionManager(){
        RoleVoter roleVoter = new RoleVoter();//角色决策器
        AuthenticatedVoter authenticatedVoter = new AuthenticatedVoter();//认证决策器
        UrlMatchVoter urlMatchVoter = new UrlMatchVoter();
        List<AccessDecisionVoter<? extends Object>> list = new ArrayList<>();
        list.add(roleVoter);
        list.add(authenticatedVoter);
        list.add(urlMatchVoter);
        AccessDecisionManager accessDecisionManager = new AffirmativeBased(list);
        return accessDecisionManager;
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
        return new GogUrlAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new GogUrlAuthenticationFailureHandler();
    }
}
