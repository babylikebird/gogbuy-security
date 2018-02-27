package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysDept;
import com.gogbuy.security.admin.modules.sys.entity.SysUserDept;
import com.gogbuy.security.admin.modules.sys.repository.SysUserDeptMapper;
import com.gogbuy.security.admin.modules.sys.service.SysUserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Yangxiufeng on 2018/1/19.
 * Time:11:30
 * ProjectName:gogbuy-security
 */
@Service
public class SysUserDeptServiceImpl implements SysUserDeptService {
    @Autowired
    private SysUserDeptMapper userDeptMapper;
    @Override
    public int deleteById(String id) {
        return userDeptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByUserId(String userId) {
        return userDeptMapper.deleteByUserId(userId);
    }

    @Override
    public int deleteByDeptId(String deptId) {
        return userDeptMapper.deleteByDeptId(deptId);
    }

    @Override
    public int save(SysUserDept record) {
        return userDeptMapper.insert(record);
    }

    @Override
    public SysUserDept findById(String id) {
        return userDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(SysUserDept record) {
        return userDeptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysUserDept record) {
        return userDeptMapper.updateByPrimaryKey(record);
    }

    @Override
    public SysDept findByUserId(String userId) {
        return userDeptMapper.findUserDeptByUserId(userId);
    }
}
