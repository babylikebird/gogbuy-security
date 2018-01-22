package com.gogbuy.security.admin;

import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysUserRole;
import com.gogbuy.security.admin.modules.sys.service.SysUserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Mr.Yangxiufeng on 2018/1/19.
 * Time:13:41
 * ProjectName:gogbuy-security
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleTest {
    @Autowired
    SysUserRoleService userRoleService;
    @Test
    public void add(){
        SysUserRole userRole = new SysUserRole();
        userRole.setId(IdWorker.getIdStr());
        userRole.setRoleId("954227154406969344");
        userRole.setUserId("953521191379034112");
        userRoleService.save(userRole);
    }

}
