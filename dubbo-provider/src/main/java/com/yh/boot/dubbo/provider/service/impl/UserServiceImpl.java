package com.yh.boot.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yh.boot.dubbo.provider.dao.UserDao;
import com.yh.model.User;
import com.yh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(interfaceClass = UserService.class, retries = 3)
@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findUserList(User user) {
        return userDao.findUserList(user);
    }
}
