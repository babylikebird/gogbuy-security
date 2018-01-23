package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysUserRole;
import com.gogbuy.security.admin.modules.sys.repository.SysUserRoleMapper;
import com.gogbuy.security.admin.modules.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:17:02
 * ProjectName:gogbuy-security
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public int deleteById(String id) {
        return userRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByUserId(String userId) {
        return userRoleMapper.deleteByUserId(userId);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return userRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public int save(SysUserRole record) {
        return userRoleMapper.insert(record);
    }

    @Override
    public List<SysUserRole> findByUserId(String userId) {
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        return findByEntity(userRole);
    }

    @Override
    public List<SysUserRole> findByRoleId(String roleId) {
        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(roleId);
        return findByEntity(userRole);
    }

    @Override
    public List<SysUserRole> findByEntity(SysUserRole userRole) {
        return userRoleMapper.findByEntity(userRole);
    }
}
