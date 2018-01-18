package com.gogbuy.security.admin.modules.sys.service.impl;

import com.gogbuy.security.admin.common.toolkit.IdWorker;
import com.gogbuy.security.admin.modules.sys.entity.SysUser;
import com.gogbuy.security.admin.modules.sys.repository.SysUserMapper;
import com.gogbuy.security.admin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Yangxiufeng on 2018/1/17.
 * Time:9:38
 * ProjectName:gogbuy-security
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public int deleteById(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysUser record) {
        record.setId(IdWorker.getIdStr());
        record.setPassword(new Md5PasswordEncoder().encodePassword(record.getPassword(),null));
        return userMapper.insert(record);
    }

    @Override
    public SysUser findById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUser findByUsername(String name) {
        return userMapper.findByUsername(name);
    }

    @Override
    public int updateByIdSelective(SysUser record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(SysUser record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
