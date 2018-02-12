package com.gogbuy.security.oauth2.modules.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-11
 * Time: 16:38
 */
public class GogUserDetails extends User {
    public GogUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public GogUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
