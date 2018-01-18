package com.gogbuy.security.admin.modules.security.toolkit;

import com.gogbuy.security.admin.modules.security.model.GogUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:10:20
 * ProjectName:gogbuy-security
 */
public class UserHolder {
    public static GogUserDetails getCurrentUser() {
        GogUserDetails user = (GogUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
