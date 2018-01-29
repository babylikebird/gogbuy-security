package com.gogbuy.security.admin;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Mr.Yangxiufeng on 2018/1/29.
 * Time:14:52
 * ProjectName:gogbuy-security
 */
public class AntTest {

    public void test(){
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("security/menu/delete/", "POST");
    }

}
