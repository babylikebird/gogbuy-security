package com.gogbuy.security.admin;

import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import com.gogbuy.security.admin.modules.sys.entity.SysPrivilege;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Mr.Yangxiufeng on 2018/1/25.
 * Time:16:16
 * ProjectName:gogbuy-security
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElementTest {
    @Autowired
    SysElementService elementService;
    @Autowired
    SysPrivilegeService privilegeService;
}
