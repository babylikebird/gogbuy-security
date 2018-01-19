package com.gogbuy.security.admin.modules.sys.repository;

import com.gogbuy.security.admin.modules.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> getDeptByUserId(String userId);

    List<SysDept> findList(Map<String, Object> map);
}