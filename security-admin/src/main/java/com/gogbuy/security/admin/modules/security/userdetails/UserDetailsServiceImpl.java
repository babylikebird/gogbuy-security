package com.gogbuy.security.admin.modules.security.userdetails;

import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import com.gogbuy.security.admin.modules.sys.service.SysDeptService;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
import com.gogbuy.security.admin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:40
 * ProjectName:gogbuy-security
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysDeptService deptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.findByUsername(username);
        if (sysUser == null){
            throw new UsernameNotFoundException("user not exist");
        }
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        GogUserDetails user = new GogUserDetails(username,sysUser.getPassword(),grantedAuthoritySet);
        user.setUser(sysUser);
        return user;
    }
}
