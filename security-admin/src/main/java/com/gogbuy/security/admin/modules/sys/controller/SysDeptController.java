package com.gogbuy.security.admin.modules.sys.controller;

import com.gogbuy.security.admin.modules.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:15:05
 * ProjectName:gogbuy-security
 */
@RestController
@RequestMapping("dept")
public class SysDeptController {
    @Autowired
    private SysDeptService deptService;
}
