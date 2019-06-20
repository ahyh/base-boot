package com.yh.service;

import com.yh.model.User;

import java.util.List;

/**
 * UserService定义，用户测试dubbo-provider、dubbo-consumer
 *
 * @author yanhuan
 */
public interface UserService {

    /**
     * 根据User中的某一个/多个信息获取User集合
     *
     * @param user 查询条件
     * @return User集合
     */
    List<User> findUserList(User user);
}
