package com.yh.service;

import com.yh.pojo.User;
import com.yh.pojo.condition.UserCondition;

import java.util.List;

/**
 * user服务
 *
 * @author yanhuan
 */
public interface UserService {

    Integer insert(User user);

    User getById(Long id);

    List<User> findUserList(UserCondition userCondition);

    Integer insertBatch(List<User> userList);

    Integer update(User user);

    Integer delete(Long id);
}
