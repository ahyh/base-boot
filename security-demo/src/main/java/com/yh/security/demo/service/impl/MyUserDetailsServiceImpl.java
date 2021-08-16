package com.yh.security.demo.service.impl;

import com.yh.security.demo.domain.User;
import com.yh.security.demo.domain.UserRole;
import com.yh.security.demo.mapper.UserMapper;
import com.yh.security.demo.service.MyUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service("myUserDetailService")
public class MyUserDetailsServiceImpl implements MyUserDetailsService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found:" + username);
        }
        List<UserRole> userRoles = userMapper.findUserRolesByUserId(user.getId());
        String userRoleStr = "";
        if (!CollectionUtils.isEmpty(userRoles)) {
            List<String> roles = userRoles.stream().map(x -> "ROLE_" + x.getRole()).collect(Collectors.toList());
            userRoleStr = StringUtils.join(roles, ",");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("null");
        if (StringUtils.isNoneBlank(userRoleStr)) {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userRoleStr);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), authorities);
    }
}
