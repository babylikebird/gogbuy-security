package com.gogbuy.security.admin.modules.security.jwt;

import com.gogbuy.security.admin.modules.security.core.UrlGrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 10:39
 */
@Component
public class JwtAuthenticationRequestMatcher implements RequestMatcher{

    private OrRequestMatcher matchers;
    /**
     * <p>需要登录的URL</p>
     */
    private Set<UrlGrantedAuthority> authoritySet;

    public void setAuthoritySet(Set<UrlGrantedAuthority> authoritySet) {
        this.authoritySet = authoritySet;
        Iterator<UrlGrantedAuthority> i = authoritySet.iterator();
        List<RequestMatcher> list = new ArrayList<>();
        while (i.hasNext()){
            UrlGrantedAuthority authority = i.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(authority.getUrl(),authority.getHttpMethod());
            list.add(requestMatcher);
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
