package com.gogbuy.security.admin.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.toolkit.PasswordEncodeUtil;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.security.toolkit.UserHolder;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import com.gogbuy.security.admin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:15:03
 * ProjectName:gogbuy-security
 */
@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public R list(Integer pageNum, Integer pageSize, SysUser user){
        List<SysUser> userList = userService.list(pageNum,pageSize,user);
        PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
        R r = R.ok();
        r.setData(pageInfo);
        return r;
    }
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public R list(SysUser user){
        if (StringUtils.isEmpty(user.getUsername())){
            return R.failure(StatusCode.FAILURE,"用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            return R.failure(StatusCode.FAILURE,"密码不能为空");
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

    @RequestMapping(value = "{id}",method = RequestMethod.POST)
    public R getUserById(@PathVariable("id") String id){
        R r = R.ok();
        r.setData(userService.findById(id));
        return r;
    }
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public R delete(@PathVariable("id") String id){
        userService.deleteById(id);
        return R.ok();
    }

    @RequestMapping(value = "getCurrentUser",method = RequestMethod.GET)
    public R getCurrentUser(){
        R r = R.ok();
        r.setData(UserHolder.getCurrentUser());
        return r;
    }
}
