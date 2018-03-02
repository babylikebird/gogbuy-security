package com.gogbuy.security.admin.modules.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.security.authentication.GogUrlAuthenticationSuccessHandler;
import com.gogbuy.security.admin.modules.security.config.TokenSettings;
import com.gogbuy.security.admin.modules.security.jwt.token.JwtToken;
import com.gogbuy.security.admin.modules.security.jwt.token.JwtTokenFactory;
import com.gogbuy.security.admin.modules.security.model.UserContext;
import com.gogbuy.security.admin.modules.security.userdetails.GogUserDetails;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-01
 * Time: 19:00
 */
public class JwtAuthenticationSuccessHandler extends GogUrlAuthenticationSuccessHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    private JwtTokenFactory jwtTokenFactory;
    private TokenSettings jwtSettings;
    public JwtAuthenticationSuccessHandler(JwtTokenFactory jwtTokenFactory,TokenSettings jwtSettings) {
        this.jwtTokenFactory = jwtTokenFactory;
        this.jwtSettings = jwtSettings;
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
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
        JwtToken accessToken = jwtTokenFactory.createAccessJwtToken(userContext);
        JwtToken refreshToken = jwtTokenFactory.createRefreshToken(userContext);
        userContext.setAccess_token(accessToken.getToken());
        userContext.setRefresh_token(refreshToken.getToken());
        userContext.setExpires_in(jwtSettings.getTokenExpirationTime() * 60 );
        r.setData(userContext);
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
