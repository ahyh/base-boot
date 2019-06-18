package com.yh.boot.mq.rocket.producer;

import com.yh.boot.mq.rocket.producer.model.User;
import com.yh.boot.mq.rocket.producer.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class UserServiceTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setName("chenJJ");
        user.setAge(28);
        user.setSalary(new BigDecimal(15000));
        Integer insert = userService.insert(user);
        Assert.assertTrue(insert == 1);
    }
}
