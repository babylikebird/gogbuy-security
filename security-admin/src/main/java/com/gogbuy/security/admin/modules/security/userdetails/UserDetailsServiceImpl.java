package com.gogbuy.security.admin.modules.security.userdetails;

import com.gogbuy.security.admin.common.utils.Constant;
import com.gogbuy.security.admin.modules.sys.entity.*;
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
    private SysPrivilegeService privilegeService;
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysElementService elementService;

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
                List<SysPrivilege> privilegeList = privilegeService.findByRoleId(role.getId());
                if (privilegeList != null && privilegeList.size() > 0){
                    for (SysPrivilege privilege : privilegeList){
                        String resourceId = privilege.getResourceId();
                        String resourceType = privilege.getResourceType();
                        if (Constant.RESOURCE_TYPE_DIRECT.equals(resourceType) || Constant.RESOURCE_TYPE_MENU.equals(resourceType)){
                            SysMenu menu = menuService.selectId(resourceId);
                            if (menu != null && menu.getCode() != null){
                                GrantedAuthority e1 = new SimpleGrantedAuthority(menu.getCode());
                                grantedAuthoritySet.add(e1);
                            }
                        }else {
                            SysElement element = elementService.selectById(resourceId);
                            if (element != null && element.getCode() != null){
                                GrantedAuthority e2 = new SimpleGrantedAuthority(element.getCode());
                                grantedAuthoritySet.add(e2);
                            }
                        }
                    }
                }
            }
        }
        GogUserDetails user = new GogUserDetails(username,sysUser.getPassword(),grantedAuthoritySet);
        user.setUser(sysUser);
        return user;
    }
}
