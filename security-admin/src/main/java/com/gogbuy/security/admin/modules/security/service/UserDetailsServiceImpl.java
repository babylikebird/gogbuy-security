package com.gogbuy.security.admin.modules.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mr.Yangxiufeng on 2018/1/16.
 * Time:14:40
 * ProjectName:gogbuy-security
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        User user = new User(username,"e10adc3949ba59abbe56e057f20f883e",grantedAuthoritySet);
        return user;
    }
}
