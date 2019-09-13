package com.yh.spring.demo.test.service;

import com.yh.spring.demo.pojo.User;
import com.yh.spring.demo.service.UserService;
import com.yh.spring.demo.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void testGetUserById() {
        Long id = 1L;
        User user = userService.getUserById(id);
        Assert.assertTrue(user != null);
    }
}
