package com.gogbuy.security.admin.modules.sys.repository;

import com.gogbuy.security.admin.modules.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int deleteByUserId(String userId);

    int deleteByRoleId(String roleId);

    List<SysUserRole> findByEntity(SysUserRole userRole);
}