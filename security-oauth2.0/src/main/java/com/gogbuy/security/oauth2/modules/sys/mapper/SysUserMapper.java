package com.gogbuy.security.oauth2.modules.sys.mapper;

import com.gogbuy.security.oauth2.modules.sys.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author 杨秀峰123
 * @since 2018-01-30
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findByUserName(@Param("username") String username);
}
