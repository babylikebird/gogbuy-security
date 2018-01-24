package com.gogbuy.security.admin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gogbuy.security.admin.modules.sys.entity.SysPrivilege;
import com.gogbuy.security.admin.modules.sys.repository.SysPrivilegeMapper;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:14:22
 * ProjectName:gogbuy-security
 */
@Service
public class SysPrivilegeServiceImpl implements SysPrivilegeService {
    @Autowired
    private SysPrivilegeMapper privilegeMapper;

    @Override
    public int deleteId(String id) {
        return privilegeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return privilegeMapper.deleteByRoleId(roleId);
    }

    @Override
    public int deleteByResourceId(String resourceId) {
        return privilegeMapper.deleteByResourceId(resourceId);
    }

    @Override
    public int insert(SysPrivilege record) {
        return privilegeMapper.insert(record);
    }

    @Override
    public SysPrivilege selectById(String id) {
        return privilegeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(SysPrivilege record) {
        return privilegeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysPrivilege record) {
        return privilegeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysPrivilege> findByRoleId(String roleId) {
        return privilegeMapper.findByRoleId(roleId);
    }

    @Override
    public List<SysPrivilege> list(Integer pageNum, Integer pageSize, SysPrivilege privilege) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        return privilegeMapper.findByEntity(privilege);
    }

    @Override
    public List<SysPrivilege> findByEntity(SysPrivilege privilege) {
        return privilegeMapper.findByEntity(privilege);
    }
}
