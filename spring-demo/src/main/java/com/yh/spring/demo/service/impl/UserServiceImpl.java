package com.yh.spring.demo.service.impl;

import com.yh.spring.demo.event.UserAddEvent;
import com.yh.spring.demo.manager.UserSalaryManager;
import com.yh.spring.demo.mapper.UserMapper;
import com.yh.spring.demo.pojo.Salary;
import com.yh.spring.demo.pojo.User;
import com.yh.spring.demo.pojo.UserQuery;
import com.yh.spring.demo.service.UserEventPublishService;
import com.yh.spring.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * UserService实现
 *
 * @author yanhuan
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserEventPublishService userEventPublishService;

    @Resource
    private UserSalaryManager userSalaryManager;

    @Override
    public Integer insert(User user) {
        UserAddEvent userAddEvent = new UserAddEvent();
        userAddEvent.setUsername(user.getUsername());
        userAddEvent.setEmail(user.getEmail());
        userAddEvent.setUserType(1);
        userAddEvent.setUserStatus(2);
        userAddEvent.setPhone("18611862917");
        userAddEvent.setCreateTime(new Date());
        userEventPublishService.publishUserAddEvent(userAddEvent);
        logger.info("UserServiceImpl publishUserAddEvent success");
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

    @Override
    public boolean insertUserWithSalary(User user, Salary salary) {
        return userSalaryManager.insertUserWithSalary(user, salary);
    }
}
