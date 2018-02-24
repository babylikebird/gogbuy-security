package com.gogbuy.security.admin.modules.sys.controller;

import com.gogbuy.security.admin.common.annotation.AclResc;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.utils.Constant;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.entity.SysPrivilege;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:15:05
 * ProjectName:gogbuy-security
 */
@RestController
@RequestMapping("permission")
public class SysPermissionController {

    @Autowired
    private SysPrivilegeService privilegeService;
    @Autowired
    private SysRoleService roleService;

    /**
     * <p>设置角色菜单权限</p>
     * @param roleId
     * @param menuIds
     * @return
     */
    @ApiOperation(value = "设置角色菜单权限")
    @AclResc(code = "permission:setRoleMenu",name = "设置菜单",uri = "/permission/setRoleMenu",descript = "设置角色菜单权限")
    @RequestMapping(value = "setRoleMenu",method = RequestMethod.POST)
    public R setRoleMenu(String roleId,@RequestParam(value = "menuIds") String[] menuIds){
        if (roleService.findById(roleId) == null){
            return R.failure(StatusCode.FAILURE,"ID="+roleId+",角色不存在");
        }
        //1.先删除
        privilegeService.deleteMenuByRoleId(roleId);
        //2.再插入
        if (menuIds != null && menuIds.length > 0){
            for (String menuId:menuIds){
                SysPrivilege privilege = new SysPrivilege();
                privilege.setId(IdWorker.getIdStr());
                privilege.setRoleId(roleId);
                privilege.setResourceId(menuId);
                privilege.setResourceType(Constant.RESOURCE_TYPE_MENU);
                privilege.setCreateTime(new Date());
                privilegeService.insert(privilege);
            }
        }
        return R.ok();
    }
    @ApiOperation(value = "设置角色页面元素权限")
    @AclResc(code = "permission:setRoleElement",name = "设置页面元素",uri = "/permission/setRoleElement",descript = "设置角色页面权限")
    @RequestMapping(value = "setRoleElement",method = RequestMethod.POST)
    public R setRoleElement(String roleId,@RequestParam(value = "elementIds") String[] elementIds){
        if (roleService.findById(roleId) == null){
            return R.failure(StatusCode.FAILURE,"ID="+roleId+",角色不存在");
        }
        //1.先删除
        privilegeService.deleteElementByRoleId(roleId);
        //2.再插入
        if (elementIds != null && elementIds.length > 0){
            for (String menuId : elementIds){
                SysPrivilege privilege = new SysPrivilege();
                privilege.setId(IdWorker.getIdStr());
                privilege.setRoleId(roleId);
                privilege.setResourceId(menuId);
                privilege.setResourceType(Constant.RESOURCE_TYPE_BTN);
                privilege.setCreateTime(new Date());
                privilegeService.insert(privilege);
            }
        }
        return R.ok();
    }
    @ApiOperation(value = "设置角色权限")
    @AclResc(code = "permission:setRolePrivilege",name = "设置角色权限(含菜单权限、操作权限)",uri = "/permission/setRolePrivilege",descript = "设置角色权限")
    @RequestMapping(value = "setRolePrivilege",method = RequestMethod.POST)
    public R setRolePrivilege(String roleId,@RequestParam(value = "menuIds") String[] menuIds,@RequestParam(value = "elementIds") String[] elementIds){
        if (roleService.findById(roleId) == null){
            return R.failure(StatusCode.FAILURE,"ID="+roleId+",角色不存在");
        }
        R r = setRoleMenu(roleId,menuIds);
        r = setRoleElement(roleId,elementIds);
        return r;
    }
}
