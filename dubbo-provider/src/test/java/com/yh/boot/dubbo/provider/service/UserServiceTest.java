package com.yh.boot.dubbo.provider.service;

import com.yh.boot.dubbo.provider.DubboProviderApplicationTests;
import com.yh.model.User;
import com.yh.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceTest extends DubboProviderApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void testFindUsers() {
        List<User> userList = userService.findUserList(new User());
        userList.forEach(System.out::println);
    }
}
