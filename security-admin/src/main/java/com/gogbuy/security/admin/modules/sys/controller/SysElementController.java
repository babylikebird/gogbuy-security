package com.gogbuy.security.admin.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/22.
 * Time:17:18
 * ProjectName:gogbuy-security
 */
@RestController
@RequestMapping("element")
public class SysElementController {
    @Autowired
    private SysElementService elementService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public R save(SysElement element){
        element.setId(IdWorker.getIdStr());
        element.setCreateTime(new Date());
        elementService.insert(element);
        return R.ok();
    }
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public R list(String menuId,Integer pageNum,Integer pageSize){
        List<SysElement> list = elementService.list(menuId,pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return R.ok().setData(pageInfo);
    }
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST)
    public R delete(@PathVariable("id") String id){
        elementService.deleteById(id);
        return R.ok();
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public R update(SysElement element){
        elementService.updateById(element);
        return R.ok();
    }
}
