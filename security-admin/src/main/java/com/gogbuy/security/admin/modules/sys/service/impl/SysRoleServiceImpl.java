package com.gogbuy.security.admin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.constant.IdConstant;
import com.gogbuy.security.admin.modules.sys.entity.SysRole;
import com.gogbuy.security.admin.modules.sys.repository.SysRoleMapper;
import com.gogbuy.security.admin.modules.sys.service.SysDeptRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public R deleteById(String id) {
        if (IdConstant.SYS_ROLE_ID.equals(id)){
            return R.failure(StatusCode.FAILURE,"系统管理员角色不能删除");
        }
        //1.删除角色
        roleMapper.deleteByPrimaryKey(id);
        //2.删除用户角色
        userRoleService.deleteByRoleId(id);
        //3.删除部门角色
        deptRoleService.deleteByRoleId(id);
        //4.删除角色权限
        privilegeService.deleteByRoleId(id);
        return R.ok();
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
        record.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysRole record) {
        record.setUpdateTime(new Date());
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

    @Override
    public List<SysRole> list(Integer pageNum, Integer pageSize, SysRole role) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        return roleMapper.list(role);
    }

    @Override
    public SysRole findOneByEntity(SysRole role) {
        List<SysRole> list = findByEntity(role);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<SysRole> findByEntity(SysRole role) {
        return roleMapper.list(role);
    }

    @Override
    public SysRole findByRoleName(String roleName) {
        SysRole role = new SysRole();
        role.setRoleName(roleName);
        return findOneByEntity(role);
    }

    @Override
    public SysRole findByRoleValue(String roleValue) {
        SysRole role = new SysRole();
        role.setRoleName(roleValue);
        return findOneByEntity(role);
    }
}
