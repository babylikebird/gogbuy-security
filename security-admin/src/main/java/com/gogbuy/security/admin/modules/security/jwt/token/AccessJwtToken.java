package com.gogbuy.security.admin.modules.security.jwt.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 10:09
 */
public final class AccessJwtToken implements JwtToken{
    private final String rawToken;
    @JsonIgnore
    private Claims claims;

    public AccessJwtToken(String rawToken, Claims claims) {
        this.rawToken = rawToken;
        this.claims = claims;
    }

    @Override
    public String getToken() {
        return this.rawToken;
    }

    public Claims getClaims() {
        return claims;
    }
}
