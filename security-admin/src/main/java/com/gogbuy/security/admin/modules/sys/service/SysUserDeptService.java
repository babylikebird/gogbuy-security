package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysUserDept;

/**
 * Created by Mr.Yangxiufeng on 2018/1/18.
 * Time:16:11
 * ProjectName:gogbuy-security
 */
public interface SysUserDeptService {
    int deleteById(String id);

    int deleteByUserId(String userId);

    int deleteByDeptId(String deptId);

    int save(SysUserDept record);

    SysUserDept findById(String id);

    int updateByIdSelective(SysUserDept record);

    int updateById(SysUserDept record);

}
