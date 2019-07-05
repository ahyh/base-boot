package com.yh.boot.elasticsearch.demo.service;

import com.yh.boot.elasticsearch.demo.model.User;

import java.util.List;

public interface UserService {

    int insert(User user);

    int insertBatch(List<User> userList);

    List<User> findAll();

    List<User> findUserByUsername(String username);

    List<User> findUserByCreateTimeBetween(String start, String end);

}
