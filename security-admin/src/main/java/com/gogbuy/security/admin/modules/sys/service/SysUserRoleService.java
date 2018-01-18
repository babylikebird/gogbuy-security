package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysUserRole;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:16:10
 * ProjectName:gogbuy-security
 */
public interface SysUserRoleService {

    int deleteById(String id);

    int deleteByUserId(String userId);

    int deleteByRoleId(String roleId);

    int save(SysUserRole record);

}
