package com.gogbuy.security.admin.modules.security.oauth.endpoint;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.security.config.TokenSettings;
import com.gogbuy.security.admin.modules.security.model.UserContext;
import com.gogbuy.security.admin.modules.security.oauth.redis.RedisTokenStore;
import com.gogbuy.security.admin.modules.security.oauth.token.AccessToken;
import com.gogbuy.security.admin.modules.security.oauth.token.OauthToken;
import com.gogbuy.security.admin.modules.security.oauth.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

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
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenSettings tokenSettings;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @RequestMapping(value = "oauth/refresh",method = RequestMethod.POST)
    public R refreshToken(String refresh_token){
        RefreshToken refreshToken = redisTokenStore.readRefreshToken(refresh_token);
        if (null == refreshToken){
            return R.failure(StatusCode.FAILURE,"refreshToken不存在");
        }
        String username = refreshToken.getUsername();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
        OauthToken oauthToken = redisTokenStore.getOauthToken(authenticationToken);
        if (oauthToken != null){
            //更新过期时间
            AccessToken accessToken = oauthToken.getAccessToken();
            accessToken.setExpiresIn(tokenSettings.getTokenExpirationTime() * 60 * 1000);

            refreshToken = oauthToken.getRefreshToken();
            //更新过期时间
            refreshToken.setExpiresIn(tokenSettings.getRefreshTokenExpTime() * 60 *1000);
            redisTokenStore.storeOauthToken(oauthToken,authenticationToken);

        }else {
            oauthToken = new OauthToken();
            AccessToken accessToken = new AccessToken();
            accessToken.setAccessToken(UUID.randomUUID().toString());
            accessToken.setIssuedAt(new Date());
            accessToken.setExpiresIn(tokenSettings.getTokenExpirationTime() * 60 * 1000);
            oauthToken.setAccessToken(accessToken);
            //更新过期时间
            refreshToken.setExpiresIn(tokenSettings.getRefreshTokenExpTime() * 60 *1000);
            oauthToken.setRefreshToken(refreshToken);
            redisTokenStore.storeOauthToken(oauthToken,authenticationToken);
        }
        UserContext userContext = new UserContext();
        userContext.setAccess_token(oauthToken.getAccessToken().getAccessToken());
        userContext.setRefresh_token(oauthToken.getRefreshToken().getRefreshToken());
        userContext.setExpires_in(oauthToken.getAccessToken().getExpiresIn());
        return R.ok().setData(userContext);
    }
}
