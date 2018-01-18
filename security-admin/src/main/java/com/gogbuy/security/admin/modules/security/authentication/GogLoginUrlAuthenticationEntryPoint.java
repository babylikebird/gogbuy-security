package com.gogbuy.security.admin.modules.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.security.userdetails.GogUserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
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
                         AuthenticationException authException) throws IOException, ServletException {

        R r = R.failure(StatusCode.UNAUTHORIZED, "您还没有登录，请先登录");
        r.setDescription(authException.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //允许跨域
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = response.getWriter();
        String msg = objectMapper.writeValueAsString(r);
        writer.write(msg);
        writer.flush();
        writer.close();
    }


}
