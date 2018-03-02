package com.gogbuy.security.admin.modules.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-01
 * Time: 18:10
 */
public class GogAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        R r = new R();
        r.setDescription(exception.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        if (exception instanceof BadCredentialsException){
            r.setCode(StatusCode.ACCESS_TOKEN_FAILURE_CODE_ERROR);
            r.setMsg("认证失败:token不合法或已过期");
        }else if (exception instanceof AuthenticationServiceException){
            r.setCode(StatusCode.ACCESS_TOKEN_HEADER_CODE_ERROR);
            r.setMsg("没有携带认证标志");
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String msg = objectMapper.writeValueAsString(r);
            writer.write(msg);
        }finally {
            if (writer != null){
                writer.flush();
                writer.close();
            }
        }
    }
}
