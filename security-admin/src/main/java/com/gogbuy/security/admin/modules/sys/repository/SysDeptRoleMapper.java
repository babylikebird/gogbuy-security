package com.gogbuy.security.admin.modules.sys.repository;

import com.gogbuy.security.admin.modules.sys.entity.SysDeptRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDeptRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDeptRole record);

    int insertSelective(SysDeptRole record);

    SysDeptRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDeptRole record);

    int updateByPrimaryKey(SysDeptRole record);

    int deleteByDeptId(String deptId);

    int deleteByRoleId(String roleId);

    List<SysDeptRole> findByEntity(SysDeptRole entity);
}