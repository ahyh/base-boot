package com.yh.boot.log.demo.service;

import com.yh.boot.log.demo.domain.User;
import com.yh.boot.log.demo.domain.UserCondition;

import java.util.List;

/**
 * User表映射服务
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
