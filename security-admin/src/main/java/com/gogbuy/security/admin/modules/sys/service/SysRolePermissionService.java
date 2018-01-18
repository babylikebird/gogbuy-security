package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysPermission;
import com.gogbuy.security.admin.modules.sys.entity.SysRolePermission;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:16:11
 * ProjectName:gogbuy-security
 */
public interface SysRolePermissionService {
    int deleteById(String id);

    int deleteByRoleId(String roleId);

    int deleteByPermissionId(String permissionId);

    int save(SysRolePermission record);

    List<SysRolePermission> selectByRoleId(String roleId);

    List<SysPermission> getPermissionByRoleId(String roleId);
}
