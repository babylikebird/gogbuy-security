package com.gogbuy.security.admin.modules.sys.controller;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.modules.security.toolkit.UserHolder;
import com.gogbuy.security.admin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public R getUserById(@PathVariable("id") String id){
        R r = R.ok();
        r.setData(userService.findById(id));
        return r;
    }
    @RequestMapping(value = "getCurrentUser",method = RequestMethod.GET)
    public R getCurrentUser(){
        R r = R.ok();
        r.setData(UserHolder.getCurrentUser());
        return r;
    }
}
