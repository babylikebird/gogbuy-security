package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysPrivilege;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:14:20
 * ProjectName:gogbuy-security
 */
public interface SysPrivilegeService {
    int deleteId(String id);

    int deleteByRoleId(String roleId);

    int deleteByResourceId(String resourceId);

    int insert(SysPrivilege record);

    SysPrivilege selectById(String id);

    int updateByIdSelective(SysPrivilege record);

    int updateById(SysPrivilege record);

    List<SysPrivilege> findByRoleId(String roleId);

    List<SysPrivilege> list(Integer pageNum,Integer pageSize,SysPrivilege privilege);
}
