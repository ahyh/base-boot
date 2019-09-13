package com.yh.spring.demo.service.impl;

import com.yh.spring.demo.mapper.UserMapper;
import com.yh.spring.demo.pojo.User;
import com.yh.spring.demo.pojo.UserQuery;
import com.yh.spring.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserService实现
 *
 * @author yanhuan
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> findUserByQuery(UserQuery userQuery) {
        return userMapper.findUserList(userQuery);
    }
}
