package com.gogbuy.security.admin.modules.security.jwt.exception;

import com.gogbuy.security.admin.modules.security.jwt.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 10:12
 */
public class JwtExpiredTokenException extends AuthenticationException {
    private JwtToken token;
    public JwtExpiredTokenException(String msg) {
        super(msg);
    }
    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
