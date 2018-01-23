package com.gogbuy.security.admin.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.FieldErrorBuilder;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.entity.SysRole;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:15:04
 * ProjectName:gogbuy-security
 */
@RestController
@RequestMapping("role")
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public R list(Integer pageNum, Integer pageSize, SysRole role){
        List<SysRole> roleList = roleService.list(pageNum,pageSize,role);
        PageInfo info = new PageInfo(roleList);
        return R.ok().setData(info);
    }
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public R save(@Valid SysRole role, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String error = FieldErrorBuilder.build(bindingResult.getFieldErrors());
            R r = R.failure(StatusCode.FAILURE,error);
            return r;
        }
        SysRole sysRole = roleService.findByRoleName(role.getRoleName());
        if (sysRole != null){
            return R.failure(StatusCode.FAILURE,"角色名已存在");
        }
        sysRole = roleService.findByRoleValue(role.getRoleValue());
        if (sysRole != null){
            return R.failure(StatusCode.FAILURE,"角色编码已存在");
        }
        role.setId(IdWorker.getIdStr());
        role.setCreateTime(new Date());
        roleService.save(role);
        return R.ok();
    }
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public R delete(@PathVariable(value = "id") String id){
        roleService.deleteById(id);
        return R.ok();
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public R update(SysRole role){
        if (role.getId() == null){
            return R.failure(StatusCode.FAILURE,"角色Id不能为空");
        }
        if (StringUtils.isEmpty(role.getRoleName())||StringUtils.isEmpty(role.getRoleValue())){
            return R.failure(StatusCode.FAILURE,"角色名或角色编码不能为空");
        }
        SysRole sysRole = roleService.findByRoleName(role.getRoleName());
        if (sysRole != null && !sysRole.getId().equals(role.getId())){
            return R.failure(StatusCode.FAILURE,"角色名已存在");
        }
        sysRole = roleService.findByRoleValue(role.getRoleValue());
        if (sysRole != null && !sysRole.getId().equals(role.getId())){
            return R.failure(StatusCode.FAILURE,"角色编码已存在");
        }
        roleService.updateByIdSelective(role);
        return R.ok();
    }
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public R get(@PathVariable("id") String id){
        SysRole role = roleService.findById(id);
        return R.ok().setData(role);
    }
}
