package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysRole;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:35
 * ProjectName:gogbuy-security
 */
public interface SysRoleService {
    int deleteById(String id);

    int save(SysRole record);

    SysRole findById(String id);

    int updateByIdSelective(SysRole record);

    int updateById(SysRole record);

    List<SysRole> findRoleByUserId(String userId);

    List<SysRole> findRoleByDeptId(String deptId);
}
