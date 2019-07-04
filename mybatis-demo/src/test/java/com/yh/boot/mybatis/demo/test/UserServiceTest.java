package com.yh.boot.mybatis.demo.test;

import com.yh.boot.mybatis.demo.MybatisDemoApplicationTests;
import com.yh.boot.mybatis.demo.model.User;
import com.yh.boot.mybatis.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserServiceTest extends MybatisDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("yanhuan");
        user.setPassword("123456");
        user.setPhone("18611862917");
        user.setEmail("ahyanhuan@sina.com");
        int insert = userService.insert(user);
        Assert.assertTrue(insert == 1);
    }

    @Test
    public void testSelectByName() {
        String username = "yanhuan";
        List<User> userList = userService.findUserListByName(username);
        System.out.println(userList);
    }

    @Test
    public void testSelectByCreateTimeBetween() throws Exception {
        Date start = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-06-01 00:00:00");
        Date end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2019-06-30 23:59:59");
        List<User> userList = userService.findUserListByCreateTimeBetween(start, end);
        System.out.println(userList);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUsername("yanhuan");
        user.setUserType((byte) 2);
        user.setUserStatus((byte) 3);
        int update = userService.updateByName(user);
        Assert.assertTrue(update == 1);
    }
}
