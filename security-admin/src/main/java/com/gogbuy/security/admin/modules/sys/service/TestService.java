package com.gogbuy.security.admin.modules.sys.service;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-03-06
 * Time: 9:09
 */
public interface TestService {
//    @PreAuthorize("hasAuthority('test')")
    String test();
}
