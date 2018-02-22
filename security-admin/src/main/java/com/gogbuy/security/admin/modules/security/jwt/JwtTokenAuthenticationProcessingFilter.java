package com.gogbuy.security.admin.modules.security.jwt;

import com.gogbuy.security.admin.common.config.WebSecurityConfig;
import com.gogbuy.security.admin.modules.security.jwt.extractor.TokenExtractor;
import com.gogbuy.security.admin.modules.security.jwt.token.RawAccessJwtToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 10:53
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationFailureHandler failureHandler;
    private TokenExtractor jwtTokenExtractor;
    private TokenExtractor jwtHeaderTokenExtractor;

    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
                                                  TokenExtractor jwtTokenExtractor,TokenExtractor jwtHeaderTokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.jwtTokenExtractor = jwtTokenExtractor;
        this.jwtHeaderTokenExtractor = jwtHeaderTokenExtractor;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME);
        String  token = jwtHeaderTokenExtractor.extract(tokenPayload);
        if (StringUtils.isBlank(token)){
            tokenPayload = request.getParameter(WebSecurityConfig.ACCESS_TOKEN);
            token = jwtTokenExtractor.extract(tokenPayload);
        }
        if (StringUtils.isBlank(token)){
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }
        RawAccessJwtToken jwtToken = new RawAccessJwtToken(token);
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(jwtToken));
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
}
