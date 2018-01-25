package com.gogbuy.security.admin.modules.security.authentication;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mr.Yangxiufeng on 2018/1/23.
 * Time:9:32
 * ProjectName:gogbuy-security
 */
public class GogAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        int status = response.getStatus();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = response.getWriter();
        writer.print("{\"code\":403,\"message\":\"权限不足，请联系管理员!\"}");
        writer.flush();
    }
}
