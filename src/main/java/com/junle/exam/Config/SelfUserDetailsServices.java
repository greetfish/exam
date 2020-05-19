package com.junle.exam.Config;

import com.junle.exam.Entity.SystemInfo;
import com.junle.exam.Repository.SystemInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 根据username获取账户信息
 */
@Component
public class SelfUserDetailsServices implements UserDetailsService{
    @Autowired
    SystemInfoRepository systemInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //构建用户信息逻辑（取数据库/LDAP等用户信息）
        SystemInfo systemInfo =systemInfoRepository.getByInfoKey("password");
        SelfUserDetails userInfo = new SelfUserDetails();
        userInfo.setUsername(username);
        userInfo.setPassword(systemInfo.getInfoValue());//模拟从数据库获取该用户的密码
        Set authoritiesSet = new HashSet();
        GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");//模拟从数据库获取到该用户的角色
        authoritiesSet.add(authority);
        userInfo.setAuthorities(authoritiesSet);
        return userInfo;

    }
}
