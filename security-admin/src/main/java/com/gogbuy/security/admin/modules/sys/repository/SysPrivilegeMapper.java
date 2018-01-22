package com.gogbuy.security.admin.modules.sys.repository;

import com.gogbuy.security.admin.modules.sys.entity.SysPrivilege;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPrivilegeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPrivilege record);

    int insertSelective(SysPrivilege record);

    SysPrivilege selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPrivilege record);

    int updateByPrimaryKey(SysPrivilege record);

    int deleteByRoleId(String roleId);

    int deleteByResourceId(String resourceId);

    List<SysPrivilege> findByRoleId(String roleId);
}