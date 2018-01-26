package com.gogbuy.security.admin.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.annotation.AclResc;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.FieldErrorBuilder;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import com.gogbuy.security.admin.modules.sys.service.SysMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:17:18
 * ProjectName:gogbuy-security
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysElementService elementService;

    @ApiOperation("获取菜单树形列表")
    @AclResc(code = "menu:tree",name = "树形列表",uri = "/menu/tree",descript = "获取菜单树形列表")
    @RequestMapping(value = "tree",method = RequestMethod.POST)
    public R menuTree(){
        //TODO 获取树形结构
        return R.ok();
    }
    @ApiOperation("获取菜单列表")
    @AclResc(code = "menu:list",name = "列表",uri = "/menu/list",descript = "获取菜单列表")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public R list(Integer pageNum,Integer pageSize,SysMenu menu){
        List<SysMenu> list = menuService.list(pageNum,pageSize,menu);
        PageInfo info = new PageInfo(list);
        return R.ok().setData(info);
    }
    /**
     * <p>管理员以上才能创建菜单</p>
     * @param menu
     * @return
     */
    @ApiOperation("新增菜单")
    @AclResc(code = "menu:save",name = "新增",uri = "/menu/save",descript = "新增菜单")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public R save(@Valid SysMenu menu, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String error = FieldErrorBuilder.build(bindingResult.getFieldErrors());
            R r = R.failure(StatusCode.FAILURE,error);
            return r;
        }
        if (menuService.findByCode(menu.getCode()) != null){
            return R.failure(StatusCode.FAILURE,"菜单编码已存在");
        }
        menu.setCreateTime(new Date());
        menu.setId(IdWorker.getIdStr());
        menuService.insert(menu);
        return R.ok();
    }

    /**
     * <p>管理员以上才能修改</p>
     * @param menu
     * @return
     */
    @ApiOperation("更新菜单")
    @AclResc(code = "menu:update",name = "更新",uri = "/menu/update",descript = "更新菜单")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public R update(SysMenu menu){
        if (StringUtils.isEmpty(menu.getCode())){
            return R.failure(StatusCode.FAILURE,"菜单编码不能为空");
        }
        if (StringUtils.isEmpty(menu.getName())){
            return R.failure(StatusCode.FAILURE,"菜单名不能为空");
        }
        SysMenu sysMenu = menuService.findByCode(menu.getCode());
        if (sysMenu!=null && !sysMenu.getId().equals(menu.getId())){
            return R.failure(StatusCode.FAILURE,"菜单编码已存在");
        }
        menuService.updateByIdSelective(menu);
        return R.ok();
    }

    /**
     * <p>管理员以上才能删除</p>
     * @param id
     * @return
     */
    @ApiOperation("删除菜单")
    @AclResc(code = "menu:delete",name = "列表",uri = "/menu/delete/*",descript = "删除菜单")
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public R delete(@PathVariable("id") String id){
        return menuService.deleteById(id);
    }

    @ApiOperation("设置菜单页面元素")
    @AclResc(code = "menu:setElement",name = "设置元素",uri = "/menu/setElement",descript = "设置菜单元素")
    @RequestMapping(value = "setElement",method = RequestMethod.POST)
    public R setElement(String menuId,@RequestParam(value = "elementIds") String[] elementIds){
        if (menuService.selectId(menuId) == null){
            return R.failure(StatusCode.FAILURE,"菜单不存在，ID="+menuId);
        }
        if (elementIds != null && elementIds.length > 0){
            for (String eId:elementIds
                 ) {
                SysElement e = new SysElement();
                e.setId(eId);
                e.setMenuId(menuId);
                elementService.updateByIdSelective(e);
            }
        }
        return R.ok();
    }
}
