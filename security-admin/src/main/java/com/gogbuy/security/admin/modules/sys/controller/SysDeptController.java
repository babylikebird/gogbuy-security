package com.gogbuy.security.admin.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.annotation.AclResc;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.FieldErrorBuilder;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.entity.SysDept;
import com.gogbuy.security.admin.modules.sys.entity.SysDeptRole;
import com.gogbuy.security.admin.modules.sys.entity.SysRole;
import com.gogbuy.security.admin.modules.sys.service.SysDeptRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysDeptService;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:15:05
 * ProjectName:gogbuy-security
 */
@RestController
@RequestMapping("dept")
public class SysDeptController {
    @Autowired
    private SysDeptService deptService;
    @Autowired
    private SysDeptRoleService deptRoleService;
    @Autowired
    private SysRoleService roleService;

    @ApiOperation("部门树形列表")
    @AclResc(code = "dept:tree",name = "部门列表",uri = "/dept/tree",descript = "部门树形列表")
    @RequestMapping(value = "tree",method = RequestMethod.POST)
    public R tree(){
        return R.ok();
    }
    @ApiOperation("部门列表")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    @AclResc(code = "dept:list",name = "部门列表",uri = "/dept/list",descript = "部门列表")
    public R list(Integer pageNum, Integer pageSize, SysDept dept){
        List<SysDept> deptList = deptService.list(pageNum,pageSize,dept);
        PageInfo info = new PageInfo(deptList);
        return R.ok().setData(info);
    }
    @ApiOperation("新增部门")
    @AclResc(code = "dept:save",name = "新增",uri = "/dept/save",descript = "新增")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public R save(@Valid SysDept dept, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.failure(StatusCode.FAILURE, FieldErrorBuilder.build(bindingResult.getFieldErrors()));
        }
        if (deptService.findByOrgCode(dept.getOrgCode()) != null){
            return R.failure(StatusCode.FAILURE,"部门编码已经存在");
        }
        if (deptService.findByName(dept.getDeptName()) != null){
            return R.failure(StatusCode.FAILURE,"部门已经存在");
        }
        dept.setId(IdWorker.getIdStr());
        dept.setCreateTime(new Date());
        deptService.save(dept);
        return R.ok();
    }
    @ApiOperation("删除部门")
    @AclResc(code = "dept:delete",name = "删除",uri = "/dept/delete/*",descript = "删除")
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public R delete(@PathVariable("id") String id){
        return deptService.deleteById(id);
    }
    @ApiOperation("修改部门")
    @AclResc(code = "dept:update",name = "更新",uri = "/dept/update",descript = "更新部门")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public R update(SysDept dept){
        if (StringUtils.isEmpty(dept.getDeptName())){
            return R.failure(StatusCode.FAILURE,"部门名称不能为空");
        }
        if (StringUtils.isEmpty(dept.getOrgCode())){
            return R.failure(StatusCode.FAILURE,"部门编码不能为空");
        }
        SysDept sysDept = deptService.findByName(dept.getDeptName());
        if (sysDept != null && !sysDept.getId().equals(dept.getId())){
            return R.failure(StatusCode.FAILURE,"部门已经存在");
        }
        sysDept = deptService.findByOrgCode(dept.getOrgCode());
        if (sysDept != null && !sysDept.getId().equals(dept.getId())){
            return R.failure(StatusCode.FAILURE,"部门编码已经存在");
        }
        deptService.updateByIdSelective(dept);
        return R.ok();
    }
    @ApiOperation("设置部门拥有的角色")
    @AclResc(code = "dept:setRole",name = "设置部门角色",uri = "/dept/setRole",descript = "设置部门角色")
    @RequestMapping(value = "setRole",method = RequestMethod.POST)
    public R setRole(String deptId,@RequestParam(value = "roleIds") String[] roleIds){
        if (StringUtils.isEmpty(deptId)){
            return R.failure(StatusCode.FAILURE,"部门ID不能为空");
        }
        if (deptService.findById(deptId) == null){
            return R.failure(StatusCode.FAILURE,"不存在该部门，ID="+deptId);
        }
        //1.删除原来的
        deptRoleService.deleteByDeptId(deptId);
        //2.添加
        if (roleIds != null && roleIds.length > 0){
            for (String id:roleIds
                 ) {
                SysDeptRole deptRole = new SysDeptRole();
                deptRole.setId(IdWorker.getIdStr());
                deptRole.setDeptId(deptId);
                deptRole.setRoleId(id);
                deptRoleService.save(deptRole);
            }
        }
        return R.ok();
    }
    @ApiOperation("添加部门角色")
    @AclResc(code = "dept:addRole",name = "添加部门角色",uri = "/dept/addRole",descript = "添加部门角色")
    @RequestMapping(value = "addRole",method = RequestMethod.POST)
    public R addRole(String deptId,String roleId){
        if (StringUtils.isEmpty(deptId) || StringUtils.isEmpty(roleId)){
            return R.failure(StatusCode.FAILURE,"部门ID或角色ID不能为空");
        }
        SysDeptRole deptRole = new SysDeptRole();
        deptRole.setRoleId(roleId);
        deptRole.setDeptId(deptId);
        List<SysDeptRole> list = deptRoleService.findByEntity(deptRole);
        if (list != null && list.size() > 0){
            //已经有了，直接返回
            return R.ok();
        }
        deptRoleService.save(deptRole);
        return R.ok();
    }
    @ApiOperation(value = "获取部门角色")
    @AclResc(code = "dept:getDeptRole",name = "获取部门角色",uri = "/dept/getDeptRole/*",descript = "获取部门角色")
    @RequestMapping(value = "getDeptRole/{deptId}",method = RequestMethod.POST)
    public R getUserRole(@PathVariable("deptId") String deptId){
        List<SysRole> roleList = roleService.findRoleByDeptId(deptId);
        return R.ok().setData(roleList);
    }
}
