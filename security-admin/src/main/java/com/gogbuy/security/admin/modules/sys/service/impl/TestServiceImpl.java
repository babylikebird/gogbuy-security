package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-06
 * Time: 9:10
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "test";
    }
}
