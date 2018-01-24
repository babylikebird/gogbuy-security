package com.gogbuy.security.admin.modules.sys.service;

import com.gogbuy.security.admin.common.model.R;
import com.gogbuy.security.admin.modules.sys.entity.SysDept;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:36
 * ProjectName:gogbuy-security
 */
public interface SysDeptService {
    R deleteById(String id);

    int save(SysDept record);

    SysDept findById(String id);

    int updateByIdSelective(SysDept record);

    int updateById(SysDept record);

    List<SysDept> getDeptByUserId(String userId);

    List<SysDept> list(Integer pageNum,Integer pageSize,SysDept dept);

    List<SysDept> findByParentId(String parentId);

    List<SysDept> findByEntity(SysDept dept);

    SysDept findByOrgCode(String orgCode);

    SysDept findByName(String name);
}
