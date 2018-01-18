package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysPermission;
import com.gogbuy.security.admin.modules.sys.entity.SysRolePermission;
import com.gogbuy.security.admin.modules.sys.repository.SysRoleMapper;
import com.gogbuy.security.admin.modules.sys.repository.SysRolePermissionMapper;
import com.gogbuy.security.admin.modules.sys.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:16:48
 * ProjectName:gogbuy-security
 */
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public int deleteById(String id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return rolePermissionMapper.deleteByRoleId(roleId);
    }

    @Override
    public int deleteByPermissionId(String permissionId) {
        return rolePermissionMapper.deleteByPermissionId(permissionId);
    }

    @Override
    public int save(SysRolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    @Override
    public List<SysRolePermission> selectByRoleId(String roleId) {
        return rolePermissionMapper.selectByRoleId(roleId);
    }

    @Override
    public List<SysPermission> getPermissionByRoleId(String roleId) {
        return null;
    }
}
