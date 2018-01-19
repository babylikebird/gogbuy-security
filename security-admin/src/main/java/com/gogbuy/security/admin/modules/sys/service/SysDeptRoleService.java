package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysDeptRole;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:16:12
 * ProjectName:gogbuy-security
 */
public interface SysDeptRoleService {
    int deleteById(String id);

    int deleteByDeptId(String deptId);

    int deleteByRoleId(String roleId);

    int save(SysDeptRole record);

    SysDeptRole findById(String id);

    List<SysDeptRole> findByEntity(SysDeptRole entity);
}
