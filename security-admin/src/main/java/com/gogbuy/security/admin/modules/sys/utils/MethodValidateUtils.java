package com.gogbuy.security.admin.modules.sys.utils;

import org.springframework.http.HttpMethod;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-13
 * Time: 11:03
 */
public class MethodValidateUtils {

    public static boolean isMethodValidate(String method){
        HttpMethod httpMethod = HttpMethod.resolve(method);
        if (httpMethod == null){
            return false;
        }
        return true;
    }

}
