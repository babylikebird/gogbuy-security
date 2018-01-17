package com.gogbuy.security.admin.common.config;

import com.gogbuy.security.admin.common.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:04
 * ProjectName:gogbuy-security
 */
@Configuration
public class FilterConfig {
    /**
     * <p>这个xss过滤</p>
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        XssFilter xssFilter = new XssFilter();
        registration.setFilter(xssFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
