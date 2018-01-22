package com.gogbuy.security.admin;

import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysRole;
import com.gogbuy.security.admin.modules.sys.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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

    @Test
    public void addRole(){
        SysRole role = new SysRole();
        role.setId(IdWorker.getIdStr());
        role.setRoleName("系统管理员");
        role.setRoleValue("SysAdmin");
        role.setCreateTime(new Date());
        roleService.save(role);
    }
}
