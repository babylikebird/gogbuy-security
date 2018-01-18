package com.gogbuy.security.admin.modules.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:9:09
 * ProjectName:gogbuy-security
 */
public class GogUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    public GogUrlAuthenticationSuccessHandler() {
    }

    public GogUrlAuthenticationSuccessHandler(String defaultTargetUrl) {
        super(defaultTargetUrl);
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //直接返回不跳转
        R r = R.failure(StatusCode.SUCCESS,"登录成功");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //允许跨域
        response.addHeader("Access-Control-Allow-Origin", "*");
        r.setData(authentication.getPrincipal());
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
