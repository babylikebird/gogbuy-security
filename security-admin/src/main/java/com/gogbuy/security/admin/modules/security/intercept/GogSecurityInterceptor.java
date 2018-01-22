package com.gogbuy.security.admin.modules.security.intercept;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;

/**
 * Created by Mr.Yangxiufeng on 2018/1/19.
 * Time:15:54
 * ProjectName:gogbuy-security
 */
public class GogSecurityInterceptor extends AbstractSecurityInterceptor {

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;

    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }

}
