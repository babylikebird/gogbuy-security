package com.gogbuy.security.admin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:16:39
 * ProjectName:gogbuy-security
 */
public class SecurityAdminApplicationInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SecurityAdminApplication.class);
    }
}
