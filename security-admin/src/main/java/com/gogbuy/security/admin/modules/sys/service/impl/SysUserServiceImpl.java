package com.gogbuy.security.admin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import com.gogbuy.security.admin.modules.sys.repository.SysUserMapper;
import com.gogbuy.security.admin.modules.sys.service.SysUserDeptService;
import com.gogbuy.security.admin.modules.sys.service.SysUserRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:38
 * ProjectName:gogbuy-security
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysUserDeptService userDeptService;

    @Override
    public int deleteById(String id) {
        userMapper.deleteByPrimaryKey(id);
        //删除用户角色
        userRoleService.deleteByUserId(id);
        //删除用户部门
        userDeptService.deleteByUserId(id);
        return 1;
    }

    @Override
    public int save(SysUser record) {
        record.setId(IdWorker.getIdStr());
        return userMapper.insert(record);
    }

    @Override
    public SysUser findById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUser findByUsername(String name) {
        return userMapper.findByUsername(name);
    }

    @Override
    public int updateByIdSelective(SysUser record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysUser record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysUser> list(Integer pageNum, Integer pageSize, SysUser user) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        return userMapper.list(user);
    }
}