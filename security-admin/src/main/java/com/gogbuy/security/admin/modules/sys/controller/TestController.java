package com.gogbuy.security.admin.modules.sys.controller;

import com.gogbuy.security.admin.modules.sys.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-06
 * Time: 9:11
 */
@RestController
public class TestController {
    @Autowired
    TestService testService;
    @RequestMapping(value = "test",method = RequestMethod.POST)
    public String test(){
        return testService.test();
    }

}
