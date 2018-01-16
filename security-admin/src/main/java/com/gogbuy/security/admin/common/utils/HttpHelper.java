package com.gogbuy.security.admin.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:15:05
 * ProjectName:gogbuy-security
 */
public class HttpHelper {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
        return isAjax;
    }
}
