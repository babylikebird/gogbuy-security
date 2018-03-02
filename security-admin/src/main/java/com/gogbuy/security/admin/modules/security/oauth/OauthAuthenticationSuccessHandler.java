package com.gogbuy.security.admin.modules.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.security.authentication.GogUrlAuthenticationSuccessHandler;
import com.gogbuy.security.admin.modules.security.jwt.token.JwtTokenFactory;
import com.gogbuy.security.admin.modules.security.model.UserContext;
import com.gogbuy.security.admin.modules.security.oauth.redis.RedisTokenStore;
import com.gogbuy.security.admin.modules.security.oauth.token.AccessToken;
import com.gogbuy.security.admin.modules.security.oauth.token.OauthToken;
import com.gogbuy.security.admin.modules.security.oauth.token.RefreshToken;
import com.gogbuy.security.admin.modules.security.userdetails.GogUserDetails;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import org.springframework.security.core.Authentication;
import com.gogbuy.security.admin.modules.security.config.TokenSettings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-01
 * Time: 18:56
 */
public class OauthAuthenticationSuccessHandler extends GogUrlAuthenticationSuccessHandler{
    private ObjectMapper objectMapper = new ObjectMapper();
    private TokenSettings jwtSettings;
    private RedisTokenStore redisTokenStore;
    public OauthAuthenticationSuccessHandler(TokenSettings jwtSettings,RedisTokenStore redisTokenStore) {
        this.jwtSettings = jwtSettings;
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        this.redisTokenStore = redisTokenStore;
    }
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //直接返回不跳转
        R r = R.failure(StatusCode.SUCCESS,"登录成功");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //允许跨域
        response.addHeader("Access-Control-Allow-Origin", "*");
        GogUserDetails user = (GogUserDetails) authentication.getPrincipal();
        SysUser sysUser = user.getUser();

        UserContext userContext = UserContext.create(sysUser.getId(),sysUser.getUsername(),user.getAuthorities());
        userContext.setId(sysUser.getId());
        userContext.setUsername(sysUser.getUsername());
        userContext.setAvatar(sysUser.getAvatar());
        userContext.setEmail(sysUser.getEmail());
        userContext.setMobile(sysUser.getMobile());
        userContext.setStatus(sysUser.getStatus());
        r.setData(userContext);
        OauthToken oauthToken = redisTokenStore.getOauthToken(authentication);
        if (oauthToken != null){
            redisTokenStore.storeOauthToken(oauthToken,authentication);
        }else {
            ////////////////////////////////
            AccessToken accessToken = new AccessToken();
            accessToken.setAccessToken(UUID.randomUUID().toString());
            accessToken.setIssuedAt(new Date());
            accessToken.setExpiresIn(jwtSettings.getTokenExpirationTime() * 60 * 1000);
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setUsername(sysUser.getUsername());
            refreshToken.setRefreshToken(UUID.randomUUID().toString());
            refreshToken.setExpiresIn(jwtSettings.getRefreshTokenExpTime() * 60 * 1000);
            oauthToken = new OauthToken();
            oauthToken.setAccessToken(accessToken);
            oauthToken.setRefreshToken(refreshToken);
            redisTokenStore.storeOauthToken(oauthToken,authentication);
            //////////////////////////////////////
        }
        AccessToken accessToken = oauthToken.getAccessToken();
        userContext.setAccess_token(accessToken.getAccessToken());
        userContext.setRefresh_token(oauthToken.getRefreshToken().getRefreshToken());
        userContext.setExpires_in(accessToken.getExpiresIn());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String msg = objectMapper.writeValueAsString(r);
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                writer.flush();
                writer.close();
            }
        }
    }
}
