package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysDeptRole;
import com.gogbuy.security.admin.modules.sys.repository.SysDeptRoleMapper;
import com.gogbuy.security.admin.modules.sys.service.SysDeptRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:16:18
 * ProjectName:gogbuy-security
 */
@Service
public class SysDeptRoleServiceImpl implements SysDeptRoleService {
    @Autowired
    private SysDeptRoleMapper deptRoleMapper;

    @Override
    public int deleteById(String id) {
        return deptRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByDeptId(String deptId) {
        return 0;
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return 0;
    }

    @Override
    public int save(SysDeptRole record) {
        return 0;
    }

    @Override
    public SysDeptRole findById(String id) {
        return deptRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysDeptRole findByEntity(SysDeptRole entity) {
        return null;
    }
}
