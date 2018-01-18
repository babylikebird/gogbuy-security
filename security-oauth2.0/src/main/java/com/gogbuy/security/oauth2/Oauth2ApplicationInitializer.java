package com.gogbuy.security.oauth2;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:14:24
 * ProjectName:gogbuy-security
 */
public class Oauth2ApplicationInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Oauth2Application.class);
    }
}
