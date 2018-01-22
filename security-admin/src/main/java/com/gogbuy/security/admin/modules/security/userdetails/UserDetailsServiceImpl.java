package com.gogbuy.security.admin.modules.security.userdetails;

import com.gogbuy.security.admin.modules.sys.entity.SysDept;
import com.gogbuy.security.admin.modules.sys.entity.SysPermission;
import com.gogbuy.security.admin.modules.sys.entity.SysRole;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import com.gogbuy.security.admin.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.findByUsername(username);
        if (sysUser == null){
            throw new UsernameNotFoundException("user not exist");
        }
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        String userId = sysUser.getId();
        List<SysRole> roleList = roleService.findRoleByUserId(userId);
        List<SysDept> deptList = deptService.getDeptByUserId(userId);
        /**
         * <p>获取部门拥有的角色</p>
         */
        if (deptList != null && deptList.size() > 0){
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
        if (roleList != null && roleList.size() > 0){
            for (SysRole role : roleList){
                /**
                 * 设置角色
                 */
                GrantedAuthority grant = new SimpleGrantedAuthority("ROLE_"+role.getRoleValue());
                grantedAuthoritySet.add(grant);
                /**
                 * <p>设置权限</p>
                 */
                List<SysPermission> permissionList = permissionService.findPermissionByRoleId(role.getId());
                if (permissionList != null && permissionList.size() > 0){
                    for (SysPermission p : permissionList){
                        GrantedAuthority g = new SimpleGrantedAuthority(p.getPerms());
                        grantedAuthoritySet.add(g);
                    }
                }
            }
        }

        GogUserDetails user = new GogUserDetails(username,sysUser.getPassword(),grantedAuthoritySet);
        user.setUser(sysUser);
        return user;
    }
}
