package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:35
 * ProjectName:gogbuy-security
 */
public interface SysUserService {
    int deleteById(String id);

    int save(SysUser record);

    SysUser findById(String id);

    SysUser findByUsername(String name);

    int updateByIdSelective(SysUser record);

    int updateById(SysUser record);

    List<SysUser> list(Integer pageNum, Integer pageSize, SysUser user);
}
