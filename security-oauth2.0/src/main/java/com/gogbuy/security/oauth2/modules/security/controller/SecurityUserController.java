package com.gogbuy.security.oauth2.modules.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-12
 * Time: 13:38
 */
@RestController
public class SecurityUserController {
    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }
}
