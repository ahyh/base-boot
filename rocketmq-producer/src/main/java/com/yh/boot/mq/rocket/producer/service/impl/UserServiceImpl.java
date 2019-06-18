package com.yh.boot.mq.rocket.producer.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.yh.boot.mq.rocket.producer.dao.UserDao;
import com.yh.boot.mq.rocket.producer.model.User;
import com.yh.boot.mq.rocket.producer.model.condition.UserCondition;
import com.yh.boot.mq.rocket.producer.mq.interf.MqProducer;
import com.yh.boot.mq.rocket.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * user服务实现
 *
 * @author yanhuan
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired(required = false)
    private MqProducer mqProducer;

    @Override
    public Integer insert(User user) {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertAndSend2RocketMq(User user) {
        Preconditions.checkNotNull(user);
        Integer insert = userDao.insert(user);
        if (insert == 1) {
            boolean send = mqProducer.send("User-Add", "User-Add1", UUID.randomUUID().toString(), JSON.toJSONString(user));
            if (!send) {
                throw new RuntimeException("Send RocketMq false");
            }
        } else {
            return false;
        }
        return true;
    }
}
