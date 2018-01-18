package com.gogbuy.security.admin.modules.sys.repository;

import com.gogbuy.security.admin.modules.sys.entity.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRolePermission record);

    int deleteByRoleId(String roleId);

    int deleteByPermissionId(@Param("permissionId") String permissionId);

    List<SysRolePermission> selectByRoleId(@Param("roleId") String roleId);
}