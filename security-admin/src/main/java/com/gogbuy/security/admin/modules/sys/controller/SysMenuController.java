package com.gogbuy.security.admin.modules.sys.controller;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.FieldErrorBuilder;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.entity.SysMenu;
import com.gogbuy.security.admin.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

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

    @RequestMapping(value = "menuTree",method = RequestMethod.POST)
    public R menuTree(){
        //TODO 获取树形结构
        return R.ok();
    }

    /**
     * <p>管理员以上才能创建菜单</p>
     * @param menu
     * @return
     */
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
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public R delete(@PathVariable("id") String id){
        return menuService.deleteById(id);
    }
}
