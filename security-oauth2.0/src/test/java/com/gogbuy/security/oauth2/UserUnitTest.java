package com.gogbuy.security.oauth2;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gogbuy.security.oauth2.modules.sys.entity.SysUser;
import com.gogbuy.security.oauth2.modules.sys.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-01-30
 * Time: 17:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserUnitTest {
    @Autowired
    SysUserService userService;
    @Test
    public void getUser(){
        List<SysUser> user = userService.selectList(new EntityWrapper<SysUser>());
        if (user != null){
            System.out.println(user);
        }
    }

}
