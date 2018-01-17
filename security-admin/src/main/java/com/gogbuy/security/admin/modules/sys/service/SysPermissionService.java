package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysPermission;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:36
 * ProjectName:gogbuy-security
 */
public interface SysPermissionService {
    int deleteById(String id);

    int save(SysPermission record);

    SysPermission selectById(String id);

    int updateByIdSelective(SysPermission record);

    int updateById(SysPermission record);
}
