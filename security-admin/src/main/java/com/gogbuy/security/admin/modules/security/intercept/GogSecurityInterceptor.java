package com.gogbuy.security.admin.modules.security.intercept;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.ServletException;
import java.io.IOException;


/**
 * Created by Mr.Yangxiufeng on 2018/1/19.
 * Time:15:54
 * ProjectName:gogbuy-security
 */
public class GogSecurityInterceptor extends FilterSecurityInterceptor {

    public GogSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource, AccessDecisionManager accessDecisionManager, AuthenticationManager authenticationManager) {
        this.setAccessDecisionManager(accessDecisionManager);
        this.setSecurityMetadataSource(securityMetadataSource);
        this.setAuthenticationManager(authenticationManager);
    }

    /**
     * <p>兼容spring security，不设置fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);</p>
     * @param fi
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        // first time this request being called, so perform security checking
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.finallyInvocation(token);
        }

        super.afterInvocation(token, null);
    }
}
