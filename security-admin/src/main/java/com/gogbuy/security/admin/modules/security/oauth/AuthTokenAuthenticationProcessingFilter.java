package com.gogbuy.security.admin.modules.security.oauth;

import com.gogbuy.security.admin.modules.security.oauth.redis.RedisTokenStore;
import com.gogbuy.security.admin.modules.security.oauth.token.OauthToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-01
 * Time: 18:06
 */
public class AuthTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private static final String HEADER_PREFIX = "Bearer ";
    private  AuthenticationFailureHandler failureHandler;

    private RedisTokenStore redisTokenStore;

    public AuthTokenAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,AuthenticationFailureHandler failureHandler,RedisTokenStore redisTokenStore) {
        super(requiresAuthenticationRequestMatcher);
        this.redisTokenStore = redisTokenStore;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String accessToken = request.getParameter("access_token");
        if (StringUtils.isEmpty(accessToken)){
            accessToken = extract(request.getHeader("Authorization"));
        }
        OauthToken oauthToken = redisTokenStore.getOauthToken(accessToken);
        if (oauthToken == null){
            throw new BadCredentialsException("Invalid access_token.");
        }
        Authentication authentication = redisTokenStore.readAuthenticationByAccessToken(oauthToken.getAccessToken().getAccessToken());
        return authentication;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
    public String extract(String header) {
        if (org.apache.commons.lang3.StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }
        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }
        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
