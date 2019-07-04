package com.yh.boot.mybatis.demo.service;

import com.yh.boot.mybatis.demo.model.User;

import java.util.Date;
import java.util.List;

/**
 * User服务
 *
 * @author yanhuan
 */
public interface UserService {

    int insert(User user);

    List<User> findUserListByName(String username);

    List<User> findUserListByCreateTimeBetween(Date start,Date end);

    int updateByName(User user);

}
