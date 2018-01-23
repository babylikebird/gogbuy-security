package com.gogbuy.security.admin.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.toolkit.PasswordEncodeUtil;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.security.toolkit.UserHolder;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import com.gogbuy.security.admin.modules.sys.service.SysUserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public R list(Integer pageNum, Integer pageSize, SysUser user){
        List<SysUser> userList = userService.list(pageNum,pageSize,user);
        PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
        R r = R.ok();
        r.setData(pageInfo);
        return r;
    }
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

    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public R delete(@PathVariable("id") String id){
        userService.deleteById(id);
        return R.ok();
    }
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
    @RequestMapping(value = "modifyPassword",method = RequestMethod.POST)
    public R modifyPassword(@NotNull(message = "id不能为空") String id,@NotEmpty(message = "密码不能为空")String newPass,String originPass){
        SysUser sysUser = userService.findById(id);
        if (sysUser == null){
            return R.failure(StatusCode.FAILURE,"用户不存在");
        }
        String originPassEncode = PasswordEncodeUtil.standEncode(originPass);
        if (!sysUser.getPassword().equals(originPassEncode)){
            return R.failure(StatusCode.FAILURE,"密码不正确");
        }
        String newPassEncode = PasswordEncodeUtil.standEncode(newPass);
        sysUser.setPassword(newPassEncode);
        sysUser.setUpdateTime(new Date());
        userService.updateByIdSelective(sysUser);
        return R.ok();
    }

    /**
     * <p>重置密码</p>
     * <p>考虑只针对管理员以上权限</p>
     * @param id
     * @return
     */
    @RequestMapping(value = "resetPassword",method = RequestMethod.POST)
    public R resetPassword(@NotNull(message = "id不能为空") String id){
        SysUser sysUser = userService.findById(id);
        if (sysUser == null){
            return R.failure(StatusCode.FAILURE,"用户不存在");
        }
        String originPassEncode = PasswordEncodeUtil.standEncode(PasswordEncodeUtil.ORIGIN_PASSWORD);
        sysUser.setPassword(originPassEncode);
        userService.updateByIdSelective(sysUser);
        return R.ok();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
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
