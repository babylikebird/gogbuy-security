package com.gogbuy.security.admin.modules.security.authentication;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/24.
 * Time:19:47
 * ProjectName:gogbuy-security
 */
public class GogAccessDecisionManager implements AccessDecisionManager {


    public GogAccessDecisionManager() {

    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url, method;
        AntPathRequestMatcher matcher;
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if(ga.getAuthority().equals("ROLE_ANONYMOUS")) {//未登录只允许访问 login 页面
                matcher = new AntPathRequestMatcher("/login");
                if (matcher.matches(request)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
