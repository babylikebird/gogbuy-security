package com.gogbuy.security.admin.common.toolkit;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:18:52
 * ProjectName:gogbuy-security
 */
public class PasswordEncodeUtil {

    public static String standEncode(String rawPass){
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
        return standardPasswordEncoder.encode(rawPass);
    }

    public static String md5Encode(String rawPass,Object salt){
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        return md5PasswordEncoder.encodePassword(rawPass,salt);
    }

}
