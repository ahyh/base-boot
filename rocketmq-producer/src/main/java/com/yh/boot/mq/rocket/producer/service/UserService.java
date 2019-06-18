package com.yh.boot.mq.rocket.producer.service;

import com.yh.boot.mq.rocket.producer.model.User;
import com.yh.boot.mq.rocket.producer.model.condition.UserCondition;

import java.util.List;

public interface UserService {

    Integer insert(User user);

    User getById(Long id);

    List<User> findUserList(UserCondition userCondition);

    Integer insertBatch(List<User> userList);

    Integer update(User user);

    Integer delete(Long id);

    /**
     * 写入数据，同时发送数据到RocketMQ
     *
     * @param user User队形
     * @return
     */
    Boolean insertAndSend2RocketMq(User user);
}
