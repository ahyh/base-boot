package com.yh.boot.log.demo.test.user.service;

import com.alibaba.fastjson.JSON;
import com.yh.boot.log.demo.LogDemoApplicationTests;
import com.yh.boot.log.demo.domain.User;
import com.yh.boot.log.demo.domain.UserCondition;
import com.yh.boot.log.demo.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserServiceTest extends LogDemoApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void testFindUsers() {
        UserCondition condition = new UserCondition();
        List<User> userList = userService.findUserList(condition);
        Assert.assertTrue(CollectionUtils.isNotEmpty(userList));
        userList.forEach(user -> System.out.println(JSON.toJSONString(user)));
    }
}
