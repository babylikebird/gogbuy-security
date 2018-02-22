package com.gogbuy.security.admin.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.annotation.AclResc;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.toolkit.PasswordEncodeUtil;
import com.gogbuy.security.admin.common.utils.Constant;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.entity.*;
import com.gogbuy.security.admin.modules.sys.model.ElementVo;
import com.gogbuy.security.admin.modules.sys.service.*;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:15:03
 * ProjectName:gogbuy-security
 */
@RestController
@RequestMapping("user")
@Validated
public class SysUserController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysDeptService deptService;
    @Autowired
    private SysUserDeptService userDeptService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysPrivilegeService privilegeService;
    @Autowired
    private SysElementService elementService;

    @ApiOperation("获取用户列表")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public R list(Integer pageNum, Integer pageSize, SysUser user){
        List<SysUser> userList = userService.list(pageNum,pageSize,user);
        PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
        R r = R.ok();
        r.setData(pageInfo);
        return r;
    }
    @ApiOperation("新增用户")
    @AclResc(code = "user:save",name = "保存用户",uri = "/user/save",descript = "保存用户")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public R save(SysUser user){
        if (StringUtils.isEmpty(user.getUsername())){
            return R.failure(StatusCode.FAILURE,"用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            return R.failure(StatusCode.FAILURE,"密码不能为空");
        }
        SysUser sysUser = userService.findByUsername(user.getUsername());
        if (sysUser != null){
            return R.failure(StatusCode.FAILURE,"用户名已存在");
        }
        user.setId(IdWorker.getIdStr());
        user.setPassword(PasswordEncodeUtil.standEncode(user.getPassword()));
        user.setCreateTime(new Date());
        if (user.getStatus() != null && user.getStatus() != 0 && user.getStatus() != 1){
            return R.failure(StatusCode.FAILURE,"用户状态值不正确");
        }else {
            user.setStatus(1);
        }
        userService.save(user);
        return R.ok();
    }
    @ApiOperation("删除用户")
    @AclResc(code = "user:delete",name = "删除",uri = "/user/delete/*",descript = "删除用户")
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public R delete(@PathVariable("id") String id){
        return userService.deleteById(id);
    }

    @ApiOperation("更新用户")
    @AclResc(code = "user:update",name = "更新",uri = "/user/update",descript = "更新用户")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public R update(@NotNull(message = "id不能为空") String id, @NotEmpty(message = "用户名不能为空") String username, String email, String mobile,
                    @Min(value = 0,message = "status 0-1之间") @Max(value = 1,message = "status 0-1之间")Integer status,
                    String avatar){
        SysUser sysUser = userService.findByUsername(username);
        if (!sysUser.getId().equals(id)){
            return R.failure(StatusCode.FAILURE,"更新失败:用户名已经存在");
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setStatus(status);
        user.setAvatar(avatar);
        user.setUpdateTime(new Date());
        userService.updateByIdSelective(user);
        return R.ok();
    }
    @ApiOperation("修改密码")
    @AclResc(code = "user:modifyPassword",name = "修改密码",uri = "/user/modifyPassword",descript = "修改密码")
    @RequestMapping(value = "modifyPassword",method = RequestMethod.POST)
    public R modifyPassword(@NotNull(message = "id不能为空") String id,@NotEmpty(message = "密码不能为空")String newPass,String originPass){
        SysUser sysUser = userService.findById(id);
        if (sysUser == null){
            return R.failure(StatusCode.FAILURE,"用户不存在");
        }
        StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        if (!passwordEncoder.matches(originPass,sysUser.getPassword())){
            return R.failure(StatusCode.FAILURE,"密码不正确");
        }
        String newPassEncode = PasswordEncodeUtil.standEncode(newPass);
        sysUser.setPassword(newPassEncode);
        sysUser.setUpdateTime(new Date());
        sysUser.setLastUpdatePasswordTime(new Date());
        userService.updateByIdSelective(sysUser);
        return R.ok();
    }

    /**
     * <p>重置密码</p>
     * <p>考虑只针对管理员以上权限</p>
     * @param id
     * @return
     */
    @ApiOperation("重置密码")
    @AclResc(code = "user:resetPassword",name = "重置密码",uri = "/user/resetPassword",descript = "重置密码")
    @RequestMapping(value = "resetPassword",method = RequestMethod.POST)
    public R resetPassword(@NotNull(message = "id不能为空") String id){
        SysUser sysUser = userService.findById(id);
        if (sysUser == null){
            return R.failure(StatusCode.FAILURE,"用户不存在");
        }
        String originPassEncode = PasswordEncodeUtil.standEncode(PasswordEncodeUtil.ORIGIN_PASSWORD);
        sysUser.setPassword(originPassEncode);
        sysUser.setLastUpdatePasswordTime(new Date());
        sysUser.setUpdateTime(new Date());
        userService.updateByIdSelective(sysUser);
        return R.ok();
    }
    @ApiOperation("通过ID获取用户")
    @AclResc(code = "user:get",name = "获取用户信息",uri = "/user/*",descript = "获取用户信息")
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public R getUserById(@PathVariable("id") String id){
        R r = R.ok();
        r.setData(userService.findById(id));
        return r;
    }
    /**
     * <p>设置角色，应该做适当的权限控制，如管理员才能设置</p>
     * @param userId
     * @param roleIds
     * @return
     */
    @ApiOperation(value = "设置用户角色")
    @AclResc(code = "user:setRole",name = "设置角色",uri = "/user/setRole",descript = "设置用户角色")
    @RequestMapping(value = "setRole",method = RequestMethod.POST)
    public R setRole(String userId,@RequestParam(value = "roleIds")String[] roleIds){
        if (userService.findById(userId) == null){
            return R.failure(StatusCode.FAILURE,"用户不存在");
        }
        //删除之前的记录
        userRoleService.deleteByUserId(userId);
        //插入记录
        if (roleIds != null && roleIds.length > 0){
            for (String roleId:roleIds){
                SysUserRole userRole = new SysUserRole();
                userRole.setId(IdWorker.getIdStr());
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoleService.save(userRole);
            }
        }
        return R.ok();
    }
    @ApiOperation("设置用户所在的部门")
    @AclResc(code = "user:setDept",name = "设置部门",uri = "/user/setDept",descript = "设置用户所在的部门")
    @RequestMapping(value = "setDept",method = RequestMethod.POST)
    public R setDept(String userId,String deptId){
        //一个用户属于一个部门
        if (userService.findById(userId) == null){
            return R.failure(StatusCode.FAILURE,"用户不存在");
        }
        if (deptService.findById(deptId) == null){
            return R.failure(StatusCode.FAILURE,"部门不存在");
        }
        userDeptService.deleteByUserId(userId);
        SysUserDept userDept = new SysUserDept();
        userDept.setDeptId(deptId);
        userDept.setUserId(userId);
        userDeptService.save(userDept);
        return R.ok();
    }

    @ApiOperation(value = "获取用户菜单")
    @AclResc(code = "user:menu",name = "用户菜单",uri = "/user/menu/*",descript = "获取用户菜单")
    @RequestMapping(value = "menu/{id}",method = RequestMethod.POST)
    public R getUserMenu(@PathVariable("id") String id){
        if (StringUtils.isEmpty(id)){
            return R.failure(StatusCode.FAILURE,"ID不能为空");
        }
        List<SysMenu> menuList = userService.getUserMenu(id);
        return R.ok().setData(menuList);
    }
    @ApiOperation(value = "获取用户角色")
    @AclResc(code = "user:getUserRole",name = "获取用户角色",uri = "/user/getUserRole/*",descript = "获取用户角色")
    @RequestMapping(value = "getUserRole/{userId}",method = RequestMethod.POST)
    public R getUserRole(@PathVariable("userId") String userId){
        List<SysRole> roleList = roleService.findRoleByUserId(userId);
        return R.ok().setData(roleList);
    }
    @ApiOperation(value = "获取用户数据操作权限")
    @AclResc(code = "user:getUserAction",name = "获取用户数据操作权限",uri = "/user/getUserAction/*",descript = "获取用户数据操作权限")
    @RequestMapping(value = "getUserAction/{userId}",method = RequestMethod.POST)
    public R getUserAction(@PathVariable("userId")String userId){
        List<SysRole> roleList = roleService.findRoleByUserId(userId);
        List<SysDept> deptList = deptService.getDeptByUserId(userId);
        if (deptList != null && !deptList.isEmpty()){
            for (SysDept dept:deptList
                    ) {
                List<SysRole> dRoleList = roleService.findRoleByDeptId(dept.getId());
                if (roleList != null && dRoleList != null){
                    roleList.addAll(dRoleList);
                }
            }
        }
        /**
         * <p>设置用户拥有的权限</p>
         */
        List<ElementVo> elementVoList = new ArrayList<>();
        if (roleList != null && roleList.size() > 0){
            for (SysRole role : roleList){
                /**
                 * <p>设置权限</p>
                 */
                List<SysPrivilege> privilegeList = privilegeService.findByRoleId(role.getId());
                if (privilegeList != null && privilegeList.size() > 0){
                    for (SysPrivilege privilege : privilegeList){
                        String resourceId = privilege.getResourceId();
                        String resourceType = privilege.getResourceType();
                        if (Constant.RESOURCE_TYPE_BTN.equals(resourceType) || Constant.RESOURCE_TYPE_URI.equals(resourceType)){
                            SysElement element = elementService.selectById(resourceId);
                            ElementVo elementVo = new ElementVo();
                            elementVo.setCode(element.getCode());
                            elementVo.setName(element.getName());
                            elementVo.setDescription(element.getDescription());
                            elementVoList.add(elementVo);
                        }
                    }
                }
            }
        }
        return R.ok().setData(elementVoList);
    }
}
