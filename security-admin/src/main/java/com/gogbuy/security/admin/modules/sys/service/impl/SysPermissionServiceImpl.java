package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysPermission;
import com.gogbuy.security.admin.modules.sys.repository.SysPermissionMapper;
import com.gogbuy.security.admin.modules.sys.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:37
 * ProjectName:gogbuy-security
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;
    @Override
    public int deleteById(String id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysPermission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public SysPermission selectById(String id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(SysPermission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysPermission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysPermission> findPermissionByRoleId(String roleId) {
        return permissionMapper.findPermissionByRoleId(roleId);
    }
}
