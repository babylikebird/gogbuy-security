package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import com.gogbuy.security.admin.modules.sys.repository.SysElementMapper;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import com.gogbuy.security.admin.modules.sys.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:13:04
 * ProjectName:gogbuy-security
 */
@Service
public class SysElementServiceImpl implements SysElementService {
    @Autowired
    private SysElementMapper elementMapper;
    @Autowired
    private SysRolePermissionService rolePermissionService;

    @Override
    public int deleteById(String id) {
        //1.删除菜单
        elementMapper.deleteByPrimaryKey(id);
        //2.删除角色权限
        rolePermissionService.deleteByPermissionId(id);
        return 1;
    }

    @Override
    public int deleteByMenuId(String menuId) {
        //1.通过菜单ID删除页面元素
        elementMapper.deleteByMenuId(menuId);
        //2.获取菜单ID下面的页面元素
        List<SysElement> elements = findByMenuId(menuId);
        if (elements != null && elements.size() > 0){
            for (SysElement element : elements){
                deleteById(element.getId());
            }
        }
        return 1;
    }

    @Override
    public int insert(SysElement record) {
        return elementMapper.insert(record);
    }

    @Override
    public SysElement selectById(String id) {
        return elementMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysElement> findByMenuId(String menuId) {
        return elementMapper.findByMenuId(menuId);
    }

    @Override
    public int updateByIdSelective(SysElement record) {
        return elementMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysElement record) {
        return elementMapper.updateByPrimaryKey(record);
    }
}
