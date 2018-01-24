package com.gogbuy.security.admin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.constant.IdConstant;
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
    public R deleteById(String id) {
        if (IdConstant.sysMenuId.contains(id)){
            return R.failure(StatusCode.FAILURE,"系统菜单不能删除");
        }
        //有子菜单的时候，不能删除，需要先删除子菜单
        List<SysMenu> menuList = findByParentId(id);
        if (menuList != null && menuList.size() > 0){
            return R.failure(StatusCode.FAILURE,"需要先删除子菜单");
        }
        //删除菜单
        menuMapper.deleteByPrimaryKey(id);
        //删除角色权限
        rolePermissionService.deleteByResourceId(id);
        //删除menu element
        elementService.deleteByMenuId(id);
        return R.ok();
    }

    @Override
    public int insert(SysMenu record) {
        return menuMapper.insertSelective(record);
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

    @Override
    public List<SysMenu> findByEntity(SysMenu menu) {
        return menuMapper.findByEntity(menu);
    }

    @Override
    public SysMenu findByCode(String code) {
        SysMenu menu = new SysMenu();
        menu.setCode(code);
        List<SysMenu> list = findByEntity(menu);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<SysMenu> list(Integer pageNum, Integer pageSize, SysMenu menu) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        return findByEntity(menu);
    }
}
