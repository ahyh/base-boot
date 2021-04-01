package com.yh.mybatis.plus.test;

import com.yh.mybatis.plus.dao.UserMapper;
import com.yh.mybatis.plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusDemoTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 需要注意此处的@Test必须是junit5包中的，如果是junit4的则不能注入UserMapper
     */
    @Test
    public void testGet() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }
}
