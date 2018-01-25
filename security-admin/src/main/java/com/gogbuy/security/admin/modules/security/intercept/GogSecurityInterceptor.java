package com.gogbuy.security.admin.modules.security.intercept;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


/**
 * Created by Mr.Yangxiufeng on 2018/1/19.
 * Time:15:54
 * ProjectName:gogbuy-security
 */
public class GogSecurityInterceptor extends FilterSecurityInterceptor{

    public GogSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource, AccessDecisionManager accessDecisionManager, AuthenticationManager authenticationManager) {
        this.setAccessDecisionManager(accessDecisionManager);
        this.setSecurityMetadataSource(securityMetadataSource);
        this.setAuthenticationManager(authenticationManager);
    }
}
