package com.gogbuy.security.admin.common.config;

import com.gogbuy.security.admin.modules.security.authentication.GogLoginUrlAuthenticationEntryPoint;
import com.gogbuy.security.admin.modules.security.authentication.GogUrlAuthenticationFailureHandler;
import com.gogbuy.security.admin.modules.security.authentication.GogUrlAuthenticationSuccessHandler;
import com.gogbuy.security.admin.modules.security.filter.GogUsernamePasswordAuthenticationFilter;
import com.gogbuy.security.admin.modules.security.userdetails.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.*;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:22
 * ProjectName:gogbuy-security
 */
@Configuration
@EnableWebSecurity
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login","/login/**").permitAll()
                // 其他地址的访问均需验证权限（需要登录）
                .anyRequest().authenticated().and()
                // 添加验证码验证
                .addFilterAt(gogUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).exceptionHandling()
                .authenticationEntryPoint(new GogLoginUrlAuthenticationEntryPoint()).and()
                // 指定登录页面的请求路径
                .formLogin()
                // 登陆处理路径
                .loginProcessingUrl("/login").permitAll().and()
                // 退出请求的默认路径为logout，下面改为signout，
                // 成功退出登录后的url可以用logoutSuccessUrl设置
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login_page").permitAll().and()
                // 关闭csrf
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略静态文件
//        web.ignoring().antMatchers("/img/**")
//                .antMatchers("/css/**")
//                .antMatchers("/js/**");
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
        auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(new Md5PasswordEncoder());
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
