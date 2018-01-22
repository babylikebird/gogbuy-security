package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysRole;
import com.gogbuy.security.admin.modules.sys.repository.SysRoleMapper;
import com.gogbuy.security.admin.modules.sys.service.SysDeptRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:38
 * ProjectName:gogbuy-security
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysDeptRoleService deptRoleService;
    @Autowired
    private SysPrivilegeService privilegeService;

    @Override
    public int deleteById(String id) {
        //1.删除角色
        roleMapper.deleteByPrimaryKey(id);
        //2.删除用户角色
        userRoleService.deleteByRoleId(id);
        //3.删除部门角色
        deptRoleService.deleteByRoleId(id);
        //4.删除角色权限
        privilegeService.deleteByRoleId(id);
        return 1;
    }

    @Override
    public int save(SysRole record) {
        return roleMapper.insert(record);
    }

    @Override
    public SysRole findById(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(SysRole record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysRole record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysRole> findRoleByUserId(String userId) {
        return roleMapper.findRoleByUserId(userId);
    }

    @Override
    public List<SysRole> findRoleByDeptId(String deptId) {
        return roleMapper.findRoleByDeptId(deptId);
    }
}
