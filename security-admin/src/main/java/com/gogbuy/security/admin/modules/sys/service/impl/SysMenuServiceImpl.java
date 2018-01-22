package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysMenu;
import com.gogbuy.security.admin.modules.sys.repository.SysMenuMapper;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import com.gogbuy.security.admin.modules.sys.service.SysMenuService;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:13:02
 * ProjectName:gogbuy-security
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{
    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysPrivilegeService rolePermissionService;
    @Autowired
    private SysElementService elementService;

    @Override
    public int deleteById(String id) {

        //有子菜单的时候，不能删除，需要先删除子菜单
        List<SysMenu> menuList = findByParentId(id);
        if (menuList != null && menuList.size() > 0){
            return -1;
        }
        //删除菜单
        menuMapper.deleteByPrimaryKey(id);
        //删除角色权限
        rolePermissionService.deleteByResourceId(id);
        //删除menu element
        elementService.deleteByMenuId(id);
        return 1;
    }

    @Override
    public int insert(SysMenu record) {
        return menuMapper.insert(record);
    }

    @Override
    public SysMenu selectId(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(SysMenu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysMenu record) {
        return menuMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysMenu> findByParentId(String parentId) {
        return menuMapper.findByParentId(parentId);
    }
}
