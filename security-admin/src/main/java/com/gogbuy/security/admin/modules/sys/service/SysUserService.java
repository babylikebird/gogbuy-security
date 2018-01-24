package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:35
 * ProjectName:gogbuy-security
 */
public interface SysUserService {
    R deleteById(String id);

    int save(SysUser record);

    SysUser findById(String id);

    SysUser findByUsername(String name);

    int updateByIdSelective(SysUser record);

    int updateById(SysUser record);

    List<SysUser> list(Integer pageNum, Integer pageSize, SysUser user);

    List<SysMenu> getUserMenu(String userId);
}
