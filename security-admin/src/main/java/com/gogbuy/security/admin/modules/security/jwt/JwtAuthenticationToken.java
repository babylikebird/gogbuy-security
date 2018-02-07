package com.gogbuy.security.admin.modules.security.jwt;

import com.gogbuy.security.admin.modules.security.jwt.token.RawAccessJwtToken;
import com.gogbuy.security.admin.modules.security.model.UserContext;
import com.gogbuy.security.admin.modules.security.userdetails.GogUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 11:05
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private RawAccessJwtToken rawAccessToken;

    private UserContext userDetails;
    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userDetails = userContext;
        super.setAuthenticated(true);
    }
    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return rawAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessToken = null;
    }
}
