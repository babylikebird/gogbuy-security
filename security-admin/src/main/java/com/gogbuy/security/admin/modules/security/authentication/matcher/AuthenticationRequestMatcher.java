package com.gogbuy.security.admin.modules.security.authentication.matcher;

import com.gogbuy.security.admin.modules.security.core.UrlGrantedAuthority;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-01
 * Time: 18:08
 */
@Component
public class AuthenticationRequestMatcher implements RequestMatcher {
    private OrRequestMatcher matchers;
    /**
     * <p>需要登录的URL</p>
     */
    private Set<UrlGrantedAuthority> authoritySet;

    public void setAuthority(Set<UrlGrantedAuthority> authoritySet) {
        this.authoritySet = authoritySet;
        Iterator<UrlGrantedAuthority> i = authoritySet.iterator();
        List<RequestMatcher> list = new ArrayList<>();
        while (i.hasNext()){
            UrlGrantedAuthority authority = i.next();
            if (!StringUtils.isBlank(authority.getUrl())){
                if (!StringUtils.isBlank(authority.getHttpMethod())){
                    RequestMatcher requestMatcher = new AntPathRequestMatcher(authority.getUrl(),authority.getHttpMethod());
                    list.add(requestMatcher);
                }else {
                    RequestMatcher requestMatcher = new AntPathRequestMatcher(authority.getUrl(),null);
                    list.add(requestMatcher);
                }
            }
        }
        matchers = new OrRequestMatcher(list);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (matchers.matches(request)){
            return true;
        }
        return false;
    }
}
