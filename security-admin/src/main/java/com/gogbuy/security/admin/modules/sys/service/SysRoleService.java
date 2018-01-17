package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysRole;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:35
 * ProjectName:gogbuy-security
 */
public interface SysRoleService {
    int deleteById(String id);

    int save(SysRole record);

    SysRole selectById(String id);

    int updateByIdSelective(SysRole record);

    int updateById(SysRole record);
}
