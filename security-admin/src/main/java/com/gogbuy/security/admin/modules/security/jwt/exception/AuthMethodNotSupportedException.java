package com.gogbuy.security.admin.modules.security.jwt.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-06
 * Time: 11:22
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}
