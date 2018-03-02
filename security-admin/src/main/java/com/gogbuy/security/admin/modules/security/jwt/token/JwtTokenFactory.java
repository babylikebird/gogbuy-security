package com.gogbuy.security.admin.modules.security.jwt.token;

import com.gogbuy.security.admin.modules.security.core.UrlGrantedAuthority;
import com.gogbuy.security.admin.modules.security.config.TokenSettings;
import com.gogbuy.security.admin.modules.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 10:05
 */
@Component
public class JwtTokenFactory {
    private final TokenSettings settings;

    @Autowired
    public JwtTokenFactory(TokenSettings settings) {
        this.settings = settings;
    }
    /**
     * Factory method for issuing new JWT Tokens.
     * @return
     */
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("userId",userContext.getId());
        if (userContext.getAuthorities() != null && !userContext.getAuthorities().isEmpty()){
            // 之前是没有权限的时候直接抛异常，现在修改，有权限的时候才设置scopes，
            // throw new IllegalArgumentException("User doesn't have any privileges");
            Set<GrantedAuthority> urlGrantedAuthorityList = userContext.getAuthorities();
            List<String> scopes = new ArrayList<>();
            Iterator<GrantedAuthority> iterator = urlGrantedAuthorityList.iterator();
            while (iterator.hasNext()){
                UrlGrantedAuthority authority = (UrlGrantedAuthority)iterator.next();
                if (StringUtils.isBlank(authority.getHttpMethod())){
                    scopes.add(authority.getUrl()+":"+authority.getAuthority());//是空，支持所有请求方式
                }else {
                    scopes.add(authority.getUrl()+":"+authority.getHttpMethod()+":"+authority.getAuthority());
                }
            }
            claims.put("scopes",scopes);
        }

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(settings.getTokenExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("userId",userContext.getId());

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(settings.getRefreshTokenExpTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}
