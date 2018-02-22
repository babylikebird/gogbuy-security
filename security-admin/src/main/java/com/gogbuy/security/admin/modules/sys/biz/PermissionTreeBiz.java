package com.gogbuy.security.admin.modules.sys.biz;

import com.gogbuy.security.admin.modules.sys.model.MenuVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-22
 * Time: 14:02
 */
public interface PermissionTreeBiz {
    List<MenuVo> getRolePermissionTree(String roleId);
}
