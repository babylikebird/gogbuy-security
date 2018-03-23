package com.gogbuy.security.admin.modules.security.oauth;

import com.gogbuy.security.admin.modules.security.oauth.redis.RedisTokenStore;
import com.gogbuy.security.admin.modules.security.oauth.token.OauthToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-06
 * Time: 13:43
 */
public class DefaultAuthenticationProcessingFilter extends AuthTokenAuthenticationProcessingFilter {
    private static final String HEADER_PREFIX = "Bearer ";

    public DefaultAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationFailureHandler failureHandler, RedisTokenStore redisTokenStore) {
        super(requiresAuthenticationRequestMatcher, failureHandler, redisTokenStore);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String accessToken = request.getParameter("access_token");
        if (StringUtils.isEmpty(accessToken)){
            accessToken = extract(request.getHeader("Authorization"));
        }
        if (accessToken == null){
            Set<GrantedAuthority> g  = new HashSet<>();
            g.add(new SimpleGrantedAuthority("ANONYMOUS"));
            return new AnonymousAuthenticationToken("ROLE_ANONYMOUS","ROLE_ANONYMOUS",g);
        }
        OauthToken oauthToken = redisTokenStore.getOauthToken(accessToken);
        if (oauthToken == null){
            throw new BadCredentialsException("Invalid access_token.");
        }
        Authentication authentication = redisTokenStore.readAuthenticationByAccessToken(oauthToken.getAccessToken().getAccessToken());
        return authentication;
    }

    public String extract(String header) {
        if (org.apache.commons.lang3.StringUtils.isBlank(header)) {
            return null;
        }
        if (header.length() < HEADER_PREFIX.length()) {
            return null;
        }
        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
