package com.gogbuy.security.admin.modules.security.oauth.token;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-01
 * Time: 15:03
 */
public class RefreshToken implements Serializable {
    private static final long serialVersionUID = 2869477259909537286L;
    private String username;
    /**
     * <p>刷新token</p>
     */
    private String refreshToken;
    /**
     * <p>过期时间</p>
     */
    private Date expiration;
    /**
     * <p>创建时间</p>
     */
    private Date issuedAt;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
