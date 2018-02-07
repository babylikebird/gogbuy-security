package com.gogbuy.security.admin.modules.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.security.jwt.exception.JwtExpiredTokenException;
import com.gogbuy.security.admin.modules.security.userdetails.GogUserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>注意：没经过拦截的URL出现认证失败的时候，到这里处理</p>
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:10:05
 * ProjectName:gogbuy-security
 */
public class GogLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint,
        InitializingBean {
    // ~ Static fields/initializers
    // =====================================================================================

    private static final Log logger = LogFactory
            .getLog(GogLoginUrlAuthenticationEntryPoint.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    public GogLoginUrlAuthenticationEntryPoint() {
    }

    // ~ Methods
    // ========================================================================================================
    @Override
    public void afterPropertiesSet() throws Exception {
    }


    /**
     * Performs the redirect (or forward) to the login form URL.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException, ServletException {
        R r = new R();
        r.setDescription(exception.getMessage());
        r.setDescription(exception.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        if (exception instanceof JwtExpiredTokenException){
            r.setCode(StatusCode.ACCESS_TOKEN_EXPIRE_CODE_ERROR);
            r.setMsg("token已经过期");
        }else if (exception instanceof BadCredentialsException){
            r.setCode(StatusCode.ACCESS_TOKEN_FAILURE_CODE_ERROR);
            r.setMsg("认证失败:token不合法");
        }else if (exception instanceof AuthenticationServiceException){
            r.setCode(StatusCode.ACCESS_TOKEN_HEADER_CODE_ERROR);
            r.setMsg("没有携带认证标志");
        }else {
            r.setCode(StatusCode.UNAUTHORIZED);
            r.setMsg("您还没有登录，请先登录");
        }
        PrintWriter writer = response.getWriter();
        String msg = objectMapper.writeValueAsString(r);
        writer.write(msg);
        writer.flush();
        writer.close();
    }
}
