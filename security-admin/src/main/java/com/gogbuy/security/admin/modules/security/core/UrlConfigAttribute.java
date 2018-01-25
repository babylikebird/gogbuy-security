package com.gogbuy.security.admin.modules.security.core;

import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mr.Yangxiufeng on 2018/1/25.
 * Time:15:21
 * ProjectName:gogbuy-security
 */
public class UrlConfigAttribute implements ConfigAttribute {
    private final HttpServletRequest httpServletRequest;

    public UrlConfigAttribute(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public String getAttribute() {
        return null;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
}
