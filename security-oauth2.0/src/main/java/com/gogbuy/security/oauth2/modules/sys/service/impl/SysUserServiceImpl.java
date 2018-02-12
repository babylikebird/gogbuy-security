package com.gogbuy.security.oauth2.modules.sys.service.impl;

import com.gogbuy.security.oauth2.modules.sys.entity.SysUser;
import com.gogbuy.security.oauth2.modules.sys.mapper.SysUserMapper;
import com.gogbuy.security.oauth2.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author 杨秀峰123
 * @since 2018-01-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Override
    public SysUser findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
