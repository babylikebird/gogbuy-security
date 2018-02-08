package com.gogbuy.security.admin.modules.security.jwt.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-08
 * Time: 12:18
 */
@Component
public class JwtTokenExtractor implements TokenExtractor {
    @Override
    public String extract(String payload) {
        if (StringUtils.isBlank(payload)){
            throw new AuthenticationServiceException("Authorization token cannot be blank!");
        }
        return payload;
    }
}
