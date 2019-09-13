package com.yh.spring.demo.service;

import com.yh.spring.demo.pojo.User;
import com.yh.spring.demo.pojo.UserQuery;

import java.util.List;

/**
 * UserService-服务接口定义
 *
 * @author yanhuan
 */
public interface UserService {

    Integer insert(User user);

    User getUserById(Long id);

    List<User> findUserByQuery(UserQuery userQuery);
}
