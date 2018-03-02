package com.gogbuy.security.admin.modules.security.oauth.token;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-01
 * Time: 14:50
 */
public class AccessToken implements Serializable{
    private static final long serialVersionUID = 2314220331408621644L;
    /**
     * 授权Token
     */
    private String accessToken;
    /**
     * <p>过期时间</p>
     */
    private Date expiration;
    /**
     * <p>创建时间</p>
     */
    private Date issuedAt;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public int getExpiresIn() {
        return expiration != null ? Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L)
                .intValue() : 0;
    }

    public void setExpiresIn(int delta) {
        setExpiration(new Date(System.currentTimeMillis() + delta));
    }
    public boolean isExpired() {
        return expiration != null && expiration.before(new Date());
    }
}
