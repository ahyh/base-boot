package com.yh.security.demo.test;

import com.yh.security.demo.domain.User;
import com.yh.security.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetById(){
        User userById = userMapper.getUserById(1L);
        Assert.assertTrue(userById != null);
    }
}

