package com.gogbuy.security.admin;

import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;
import com.gogbuy.security.admin.modules.sys.entity.SysPrivilege;
import com.gogbuy.security.admin.modules.sys.entity.SysRole;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import com.gogbuy.security.admin.modules.sys.service.SysMenuService;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/19.
 * Time:13:38
 * ProjectName:gogbuy-security
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTests {
    @Autowired
    SysRoleService roleService;
    @Autowired
    SysPrivilegeService privilegeService;
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysElementService elementService;

    @Test
    public void addRole(){
        SysRole role = new SysRole();
        role.setId(IdWorker.getIdStr());
        role.setRoleName("系统管理员");
        role.setRoleValue("SysAdmin");
        role.setCreateTime(new Date());
        roleService.save(role);
    }
    @Test
    public void setPermission(){
       List<SysMenu> menuList = menuService.findByEntity(new SysMenu());
        for (SysMenu m: menuList
             ) {
            SysPrivilege p = new SysPrivilege();
            p.setRoleId("954227154406969344");
            p.setResourceId(m.getId());
            p.setResourceType("menu");
            p.setCreateTime(new Date());
            p.setId(IdWorker.getIdStr());
            privilegeService.insert(p);
        }
        List<SysElement> elementList = elementService.findByEntity(new SysElement());
        for (SysElement e:elementList
             ) {
            SysPrivilege p = new SysPrivilege();
            p.setRoleId("954227154406969344");
            p.setResourceId(e.getId());
            p.setResourceType("button");
            p.setCreateTime(new Date());
            p.setId(IdWorker.getIdStr());
            privilegeService.insert(p);
        }
    }
}
