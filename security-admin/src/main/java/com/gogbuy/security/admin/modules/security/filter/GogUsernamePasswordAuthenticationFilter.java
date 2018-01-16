package com.gogbuy.security.admin.modules.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:45
 * ProjectName:gogbuy-security
 */
public class GogUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    // 是否开启验证码功能
    private boolean isOpenValidateCode = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /**
         * 检查校验码
         */
        if (isOpenValidateCode){
            //TODO 验证校验码
        }
        return super.attemptAuthentication(request, response);
    }
    protected void checkValidateCode(HttpServletRequest request) {

    }

}
