package com.gogbuy.security.admin.modules.security.jwt;

import com.gogbuy.security.admin.modules.security.core.UrlGrantedAuthority;
import com.gogbuy.security.admin.modules.security.jwt.config.JwtSettings;
import com.gogbuy.security.admin.modules.security.jwt.token.RawAccessJwtToken;
import com.gogbuy.security.admin.modules.security.model.UserContext;
import com.gogbuy.security.admin.modules.security.userdetails.GogUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 11:08
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider{

    private final JwtSettings jwtSettings;

    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        String subject = jwsClaims.getBody().getSubject();
        List<UrlGrantedAuthority> urlGrantedAuthorities = new ArrayList<>();
        if (scopes != null && scopes.size() > 0){
            for (String scope:scopes){
                String[] sp = scope.split(":");
                if (sp.length == 2){//* 支持所有方法
                    UrlGrantedAuthority urlGrantedAuthority = new UrlGrantedAuthority(null,sp[0],sp[1]);
                    urlGrantedAuthorities.add(urlGrantedAuthority);
                }else {
                    UrlGrantedAuthority urlGrantedAuthority = new UrlGrantedAuthority(sp[1],sp[0],sp[2]);
                    urlGrantedAuthorities.add(urlGrantedAuthority);
                }
            }
        }
        String userId = jwsClaims.getBody().get("userId",String.class);
        if (StringUtils.isEmpty(userId)){
            throw new BadCredentialsException("Invalid JWT token: userId must not be null");
        }
        UserContext userContext = UserContext.create(userId,subject,urlGrantedAuthorities);
        return new JwtAuthenticationToken(userContext,userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
