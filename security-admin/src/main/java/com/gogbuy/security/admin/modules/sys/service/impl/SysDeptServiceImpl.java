package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.modules.sys.entity.SysDept;
import com.gogbuy.security.admin.modules.sys.repository.SysDeptMapper;
import com.gogbuy.security.admin.modules.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:37
 * ProjectName:gogbuy-security
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper deptMapper;
    @Override
    public int deleteById(String id) {
        return deptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysDept record) {
        return deptMapper.insert(record);
    }

    @Override
    public SysDept selectById(String id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(SysDept record) {
        return deptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysDept record) {
        return deptMapper.updateByPrimaryKey(record);
    }
}
