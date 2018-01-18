package com.gogbuy.security.admin.modules.security.authentication;

import com.gogbuy.security.admin.common.model.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:13:53
 * ProjectName:gogbuy-security
 */
public class GogLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = response.getWriter();
        writer.print("{\"code\":200,\"message\":\"已注销\"}");
        writer.flush();
    }
}
