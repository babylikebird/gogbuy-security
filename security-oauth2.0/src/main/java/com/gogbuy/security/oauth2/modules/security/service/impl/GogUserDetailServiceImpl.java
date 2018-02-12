package com.gogbuy.security.oauth2.modules.security.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gogbuy.security.oauth2.modules.security.service.GogUserDetailService;
import com.gogbuy.security.oauth2.modules.sys.entity.SysUser;
import com.gogbuy.security.oauth2.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-11
 * Time: 16:08
 */
@Service
public class GogUserDetailServiceImpl implements GogUserDetailService {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysDeptService deptService;
    @Autowired
    private SysPrivilegeService privilegeService;
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysElementService elementService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)){
            throw new UsernameNotFoundException("用户名不能为空");
        }
        SysUser sysUser = userService.findByUserName(username);
        if (sysUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        //TODO 获取权限
        return new User(username,sysUser.getPassword(),grantedAuthoritySet);
    }
}
