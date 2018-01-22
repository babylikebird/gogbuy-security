package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.modules.sys.entity.SysElement;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:13:00
 * ProjectName:gogbuy-security
 */
public interface SysElementService {
    int deleteById(String id);

    int deleteByMenuId(String menuId);

    int insert(SysElement record);

    SysElement selectById(String id);

    List<SysElement> findByMenuId(String menuId);

    int updateByIdSelective(SysElement record);

    int updateById(SysElement record);
}
