package com.gogbuy.security.admin.modules.sys.repository;

import com.gogbuy.security.admin.modules.sys.entity.SysUserDept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserDept record);

    int insertSelective(SysUserDept record);

    SysUserDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserDept record);

    int updateByPrimaryKey(SysUserDept record);
}