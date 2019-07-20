package com.yh.boot.log.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.yh.boot.log.demo.dao.UserDao;
import com.yh.boot.log.demo.domain.User;
import com.yh.boot.log.demo.domain.UserCondition;
import com.yh.boot.log.demo.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User服务实现类
 *
 * @author yanhuan
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public Integer insert(User user) {
        Preconditions.checkNotNull(user);
        logger.info("UserServiceImpl insert user:{}", JSON.toJSONString(user));
        return userDao.insert(user);
    }

    @Override
    public User getById(Long id) {
        Preconditions.checkNotNull(id);
        logger.info("UserServiceImpl getById id:{}", id);
        return userDao.getUserById(id);
    }

    @Override
    public List<User> findUserList(UserCondition userCondition) {
        Preconditions.checkNotNull(userCondition);
        logger.info("UserServiceImpl findUserList userCondition:{}", JSON.toJSONString(userCondition));
        return userDao.findUserList(userCondition);
    }

    @Override
    public Integer insertBatch(List<User> userList) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(userList), "userList cannot empty!");
        logger.info("UserServiceImpl insertBatch userList:{}", JSON.toJSONString(userList));
        return userDao.insertBatch(userList);
    }

    @Override
    public Integer update(User user) {
        logger.info("UserServiceImpl update user:{}", JSON.toJSONString(user));
        return userDao.updateUser(user);
    }

    @Override
    public Integer delete(Long id) {
        logger.info("UserServiceImp delete id:{}", id);
        return userDao.deleteById(id);
    }
}
