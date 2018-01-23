package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:13:00
 * ProjectName:gogbuy-security
 */
public interface SysMenuService {
    R deleteById(String id);

    int insert(SysMenu record);

    SysMenu selectId(String id);

    List<SysMenu> findByEntity(SysMenu menu);

    SysMenu findByCode(String code);

    List<SysMenu> findByParentId(String parentId);

    int updateByIdSelective(SysMenu record);

    int updateById(SysMenu record);
}
