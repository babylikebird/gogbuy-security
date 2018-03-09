package com.gogbuy.security.oauth2.modules.security.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-09
 * Time: 9:39
 */
@Component
public class GogWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        OAuth2Exception oAuth2Exception;
        if (e instanceof InvalidGrantException && StringUtils.equals("坏的凭证",e.getMessage())) {
            oAuth2Exception = new InvalidGrantException("用户名或密码错误", e);
        } else if (e instanceof InternalAuthenticationServiceException) {
            oAuth2Exception = new InvalidGrantException("用户名不存在", e);
        } else {
            oAuth2Exception = (OAuth2Exception) e;
        }
        ResponseEntity<OAuth2Exception> responseEntity = super.translate(oAuth2Exception);
        ResponseEntity<OAuth2Exception> response = new ResponseEntity<OAuth2Exception>(responseEntity.getBody(), responseEntity.getHeaders(),
                        HttpStatus.valueOf(200));
        return response;
    }
}
