package com.gogbuy.security.oauth2.common.config;

import com.gogbuy.security.oauth2.modules.security.access.GogAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 9:32
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public StandardPasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().antMatchers("/login","/oauth/authorize")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new GogAccessDeniedHandler())
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .httpBasic();
    }
}
