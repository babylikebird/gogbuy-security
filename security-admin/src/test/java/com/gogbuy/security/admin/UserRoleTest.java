package com.gogbuy.security.admin;

import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysPrivilege;
import com.gogbuy.security.admin.modules.sys.entity.SysUserRole;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import com.gogbuy.security.admin.modules.sys.service.SysUserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
    @Autowired
    SysPrivilegeService privilegeService;
    @Test
    public void add(){
        SysUserRole userRole = new SysUserRole();
        userRole.setId(IdWorker.getIdStr());
        userRole.setRoleId("954227154406969344");
        userRole.setUserId("953521191379034112");
        userRoleService.save(userRole);
    }
    @Test
    public void addPrivilege(){
//        SysPrivilege privilege1 = new SysPrivilege();
//        privilege1.setId(IdWorker.getIdStr());
//        privilege1.setResourceType("button");
//        privilege1.setCreateTime(new Date());
//        privilege1.setRoleId("954227154406969344");
//        privilege1.setResourceId("961146791933542400");
//        privilegeService.insert(privilege1);
//
//        SysPrivilege privilege2 = new SysPrivilege();
//        privilege2.setId(IdWorker.getIdStr());
//        privilege2.setResourceType("button");
//        privilege2.setCreateTime(new Date());
//        privilege2.setRoleId("954227154406969344");
//        privilege2.setResourceId("961146792302641152");
//        privilegeService.insert(privilege2);
        SysPrivilege privilege2 = new SysPrivilege();
        System.out.println("eeeeeeeeee+"+privilege2.getId());
    }
}
