package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysRole;
import com.gogbuy.security.admin.modules.sys.repository.SysRoleMapper;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
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
    @Override
    public int deleteById(String id) {
        return roleMapper.deleteByPrimaryKey(id);
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
}
