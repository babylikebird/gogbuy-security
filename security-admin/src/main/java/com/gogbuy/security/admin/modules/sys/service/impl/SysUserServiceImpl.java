package com.gogbuy.security.admin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.utils.Constant;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.constant.IdConstant;
import com.gogbuy.security.admin.modules.sys.entity.*;
import com.gogbuy.security.admin.modules.sys.repository.SysUserMapper;
import com.gogbuy.security.admin.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysDeptService deptService;
    @Autowired
    private SysPrivilegeService privilegeService;
    @Autowired
    private SysMenuService menuService;

    @Override
    public R deleteById(String id) {
        if (IdConstant.SYS_ADMIN_ID.equals(id)){
            return R.failure(StatusCode.FAILURE,"不能删除系统管理员");
        }
        userMapper.deleteByPrimaryKey(id);
        //删除用户角色
        userRoleService.deleteByUserId(id);
        //删除用户部门
        userDeptService.deleteByUserId(id);
        return R.ok();
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

    @Override
    public List<SysMenu> getUserMenu(String userId) {
        List<SysRole> roleList = roleService.findRoleByUserId(userId);
        List<SysDept> deptList = deptService.getDeptByUserId(userId);
        /**
         * <p>获取部门拥有的角色</p>
         */
        if (deptList != null && deptList.size() > 0){
            for (SysDept dept:deptList) {
                List<SysRole> dRoleList = roleService.findRoleByDeptId(dept.getId());
                if (roleList != null && dRoleList != null){
                    roleList.addAll(dRoleList);
                }
            }
        }
        List<SysMenu> menuList = new ArrayList<>();
        List<String> menuIds = new ArrayList<>();
        if (roleList != null && roleList.size() > 0) {
            for (SysRole role : roleList) {
                /**
                 * <p>获取角色拥有的权限</p>
                 */
                SysPrivilege privilege = new SysPrivilege();
                privilege.setRoleId(role.getId());
                privilege.setResourceType(Constant.RESOURCE_TYPE_MENU);
                List<SysPrivilege> privilegeList = privilegeService.findByEntity(privilege);
                if (privilegeList != null && privilegeList.size() > 0) {
                    for (SysPrivilege p : privilegeList
                            ) {
                        if (!menuIds.contains(p.getId())) {
                            menuIds.add(p.getId());
                        }
                    }
                }
            }
        }
        if (menuIds.size() > 0){
            for (int i=0;i<menuIds.size();i++){
                SysMenu m = menuService.selectId(menuIds.get(i));
                if (m != null){
                    menuList.add(m);
                }
            }

        }
        return menuList;
    }
}