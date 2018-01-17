package com.gogbuy.security.admin;

import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import com.gogbuy.security.admin.modules.sys.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityAdminApplicationTests {
	@Autowired
	private SysUserService userService;
	@Test
	public void contextLoads() {
//		int i = 0;
//		while (i < 10000){
//			i++;
//			System.out.println(IdWorker.getIdStr());
//		}
		SysUser user = new SysUser();
		user.setUsername("sysAdmin");
		user.setPassword("123456");
		user.setStatus(1);
		user.setCreateTime(new Date());
		user.setMobile("13365301652");
		userService.save(user);
	}

}
