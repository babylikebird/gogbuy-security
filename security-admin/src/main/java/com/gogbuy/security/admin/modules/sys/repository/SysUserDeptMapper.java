package com.gogbuy.security.admin.modules.sys.repository;

import com.gogbuy.security.admin.modules.sys.entity.SysDept;
import com.gogbuy.security.admin.modules.sys.entity.SysUserDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserDept record);

    int insertSelective(SysUserDept record);

    SysUserDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserDept record);

    int updateByPrimaryKey(SysUserDept record);

    int deleteByUserId(String userId);

    int deleteByDeptId(String deptId);

    SysDept findUserDeptByUserId(@Param("userId") String userId);
}