package com.gogbuy.security.admin.modules.sys.repository;

import com.gogbuy.security.admin.modules.sys.entity.SysElement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysElementMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysElement record);

    int insertSelective(SysElement record);

    SysElement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysElement record);

    int updateByPrimaryKey(SysElement record);

    int deleteByMenuId(@Param("menuId") String menuId);

    List<SysElement> findByMenuId(@Param("menuId") String menuId);
}