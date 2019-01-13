package com.yh.service.impl;

import com.google.common.base.Preconditions;
import com.yh.annotations.PropAnnotation;
import com.yh.dao.UserDao;
import com.yh.pojo.User;
import com.yh.pojo.condition.UserCondition;
import com.yh.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user服务实现
 *
 * @author yanhuan
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Integer insert(@PropAnnotation(createUser = "yanhuan") User user) {
        Preconditions.checkNotNull(user);
        return userDao.insert(user);
    }

    @Override
    public User getById(Long id) {
        Preconditions.checkNotNull(id);
        return userDao.getUserById(id);
    }

    @Override
    public List<User> findUserList(UserCondition userCondition) {
        Preconditions.checkNotNull(userCondition);
        return userDao.findUserList(userCondition);
    }

    @Override
    public Integer insertBatch(List<User> userList) {
        return userDao.insertBatch(userList);
    }

    @Override
    public Integer update(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Integer delete(Long id) {
        return userDao.deleteById(id);
    }
}
