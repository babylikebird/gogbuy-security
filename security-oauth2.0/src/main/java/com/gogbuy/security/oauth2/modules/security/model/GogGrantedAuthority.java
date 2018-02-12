package com.gogbuy.security.oauth2.modules.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-11
 * Time: 16:41
 */
public class GogGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = -1333562429312175481L;

    @Override
    public String getAuthority() {
        return null;
    }
}
