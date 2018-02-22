package com.gogbuy.security.admin.modules.sys.biz.impl;

import com.gogbuy.security.admin.common.utils.Constant;
import com.gogbuy.security.admin.modules.sys.biz.PermissionTreeBiz;
import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;
import com.gogbuy.security.admin.modules.sys.entity.SysPrivilege;
import com.gogbuy.security.admin.modules.sys.model.ElementVo;
import com.gogbuy.security.admin.modules.sys.model.MenuVo;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import com.gogbuy.security.admin.modules.sys.service.SysMenuService;
import com.gogbuy.security.admin.modules.sys.service.SysPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-22
 * Time: 14:24
 */
@Component
public class PermissionTreeBizImpl implements PermissionTreeBiz {
    @Autowired
    private SysPrivilegeService privilegeService;
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysElementService elementService;
    @Override
    public List<MenuVo> getRolePermissionTree(String roleId) {
        //1.得到角色权限
        List<SysPrivilege> privilegeList = privilegeService.findByRoleId(roleId);
        List<SysMenu> roleMenuList = new ArrayList<>();
        List<SysElement> roleElementList = new ArrayList<>();
        if (privilegeList != null && privilegeList.size() > 0){
            for (SysPrivilege privilege : privilegeList){
                String resourceId = privilege.getResourceId();
                String resourceType = privilege.getResourceType();
                if (Constant.RESOURCE_TYPE_DIRECT.equals(resourceType) || Constant.RESOURCE_TYPE_MENU.equals(resourceType)){
                    SysMenu menu = menuService.selectId(resourceId);
                    roleMenuList.add(menu);
                }else {
                    SysElement element = elementService.selectById(resourceId);
                    roleElementList.add(element);
                }
            }
        }
        List<MenuVo> menuVoList = buildMenuChecked(roleMenuList,roleElementList);
        return menuVoList;
    }
    public List<MenuVo> buildMenuChecked(List<SysMenu> roleMenuList, List<SysElement> roleElementList){
        //2.获取全部菜单权限，然后设置是否可选
        List<MenuVo> allMenu = buildOriginMenuVo();
        if (roleMenuList != null && roleMenuList.size() > 0){
            for (MenuVo menu : allMenu){
                for (SysMenu rm : roleMenuList){
                    if (menu.getId().equals(rm.getId())){
                        menu.setChecked(true);
                        break;
                    }
                }
                List<ElementVo> elementVoList = menu.getElementVos();
                if (elementVoList != null && elementVoList.size() > 0 && roleElementList != null && roleElementList.size() > 0){
                    for (ElementVo elementVo : elementVoList)
                        for (SysElement element : roleElementList){
                            if (elementVo.getId().equals(element.getId())){
                                elementVo.setChecked(true);
                                break;
                            }
                        }
                }
            }
        }
        return allMenu;
    }

    /**
     * <p>原始的menuVo</p>
     * @return
     */
    private List<MenuVo> buildOriginMenuVo(){
        //2.获取全部菜单权限，然后设置是否可选
        List<SysMenu> allMenu = menuService.findByEntity(new SysMenu());
        List<MenuVo> menuVoList = new ArrayList<>();
        for (SysMenu menu : allMenu){
            MenuVo menuVo = new MenuVo(menu);
            menuVoList.add(menuVo);
            //菜单下的操作权限
            List<SysElement> elementList = elementService.findByMenuId(menu.getId());
            if (elementList != null && !elementList.isEmpty()){
                List<ElementVo> elementVoList = new ArrayList<>();
                for (SysElement element : elementList){
                    ElementVo elementVo = new ElementVo(element);
                    elementVoList.add(elementVo);
                }
                menuVo.setElementVos(elementVoList);
            }
        }
        return menuVoList;
    }

    /**
     * <p>递归创建菜单树形列表</p>
     * @param menuVoList
     * @return
     */
    public List<MenuVo> recursiveTree(List<MenuVo> menuVoList){
        return null;
    }
}
