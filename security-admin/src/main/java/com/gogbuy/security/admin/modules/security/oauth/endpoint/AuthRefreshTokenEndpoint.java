package com.gogbuy.security.admin.modules.security.oauth.endpoint;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.modules.security.oauth.redis.RedisTokenStore;
import com.gogbuy.security.admin.modules.security.oauth.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-02
 * Time: 9:40
 */
@RestController
public class AuthRefreshTokenEndpoint {
    @Autowired
    private RedisTokenStore redisTokenStore;
    @RequestMapping(value = "oauth/refresh",method = RequestMethod.POST)
    public R refreshToken(String refresh_token){
        RefreshToken refreshToken = redisTokenStore.readRefreshToken(refresh_token);

        return null;
    }
}
