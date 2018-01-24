package com.gogbuy.security.admin;

import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;
import com.gogbuy.security.admin.modules.sys.service.SysMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Mr.Yangxiufeng on 2018/1/24.
 * Time:10:34
 * ProjectName:gogbuy-security
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {
    @Autowired
    private SysMenuService menuService;
    @Test
    public void addMenu(){
        SysMenu menu = new SysMenu();
        menu.setName("系统设置");
        menu.setId(IdWorker.getIdStr());
        menu.setCode("sys_manager");
        menu.setLevel(1);
        menu.setOrderNum(100);
        menu.setCreateTime(new Date());
        menuService.insert(menu);
    }
    @Test
    public void addChildMenu(){
        SysMenu menu = new SysMenu();
        menu.setName("用户管理");
        menu.setId(IdWorker.getIdStr());
        menu.setParentId("955993501461671936");
        menu.setCode("sys_user_manager");
        menu.setLevel(2);
        menu.setOrderNum(1);
        menu.setUri("/user/list");
        menu.setCreateTime(new Date());
        menuService.insert(menu);

        SysMenu menu1 = new SysMenu();
        menu1.setName("角色管理");
        menu1.setId(IdWorker.getIdStr());
        menu1.setParentId("955993501461671936");
        menu1.setCode("sys_role_manager");
        menu1.setUri("/role/list");
        menu1.setLevel(2);
        menu1.setOrderNum(2);
        menu1.setCreateTime(new Date());
        menuService.insert(menu1);

        SysMenu menu2 = new SysMenu();
        menu2.setName("菜单管理");
        menu2.setId(IdWorker.getIdStr());
        menu2.setParentId("955993501461671936");
        menu2.setCode("sys_menu_manager");
        menu2.setUri("/menu/list");
        menu2.setLevel(2);
        menu2.setOrderNum(3);
        menu2.setCreateTime(new Date());
        menuService.insert(menu2);

        SysMenu menu3 = new SysMenu();
        menu3.setName("部门管理");
        menu3.setId(IdWorker.getIdStr());
        menu3.setParentId("955993501461671936");
        menu3.setCode("sys_dept_manager");
        menu3.setUri("/dept/list");
        menu3.setLevel(2);
        menu3.setOrderNum(4);
        menu3.setCreateTime(new Date());
        menuService.insert(menu3);
    }
}
