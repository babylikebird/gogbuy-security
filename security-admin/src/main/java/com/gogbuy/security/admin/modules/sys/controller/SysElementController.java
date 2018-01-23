package com.gogbuy.security.admin.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.common.toolkit.FieldErrorBuilder;
import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.common.utils.StatusCode;
import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import com.gogbuy.security.admin.modules.sys.service.SysElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public R save(@Valid SysElement element,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String error = FieldErrorBuilder.build(bindingResult.getFieldErrors());
            R r = R.failure(StatusCode.FAILURE,error);
            return r;
        }
        if (elementService.findByCode(element.getCode())!= null){
            return R.failure(StatusCode.FAILURE,"页面元素编码已经存在");
        }
        element.setId(IdWorker.getIdStr());
        element.setCreateTime(new Date());
        elementService.insertSelective(element);
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
        if (StringUtils.isEmpty(element.getName())){
            return R.failure(StatusCode.FAILURE,"页面元素名不能为空");
        }
        if (StringUtils.isEmpty(element.getCode())){
            return R.failure(StatusCode.FAILURE,"页面元素编码不能为空");
        }
        SysElement sysElement = elementService.findByCode(element.getCode());
        if (sysElement != null && !sysElement.getId().equals(element.getId())){
            return R.failure(StatusCode.FAILURE,"页面元素编码已经存在");
        }
        elementService.updateById(element);
        return R.ok();
    }
}
