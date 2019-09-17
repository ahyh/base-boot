package com.yh.spring.demo.test.service;

import com.yh.spring.demo.pojo.Salary;
import com.yh.spring.demo.pojo.User;
import com.yh.spring.demo.service.UserService;
import com.yh.spring.demo.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void testGetUserById() {
        Long id = 1L;
        User user = userService.getUserById(id);
        Assert.assertTrue(user != null);
    }

    @Test
    public void insertUserWithSalary() {
        User user = new User();
        user.setUsername("u22");
        user.setPassword("1234567");
        user.setEmail("ahyanhuan@1226.com");
        user.setPhone("18611862917");
        user.setUserStatus(1);
        user.setUserType(2);
        user.setCreateUser("yh");
        user.setCreateTime(new Date());
        user.setUpdateUser("yh");
        user.setUpdateTime(new Date());

        Salary salary = new Salary();
        salary.setName(user.getUsername()+"zm");
        salary.setAge(21);
        salary.setCompany("zmzm");
        salary.setSalary(new BigDecimal("299.9"));
        salary.setSex((byte) 1);
        salary.setUpdateUser(user.getUpdateUser());
        salary.setCreateUser(user.getCreateUser());

        boolean b = userService.insertUserWithSalary(user, salary);
        Assert.assertTrue(b);
    }
}
