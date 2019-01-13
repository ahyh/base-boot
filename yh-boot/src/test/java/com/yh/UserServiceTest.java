package com.yh;

import com.yh.pojo.User;
import com.yh.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void testAdd() {
        User user = new User();
        user.setUsername("yanhuan");
        user.setPassword("123456");
        user.setEmail("ahyanhuan@163.com");
        user.setUserType(1);
        user.setUserStatus(1);
        user.setPhone("176112345667");
        user.setCreateTime(new Date());
        user.setCreateUser("yanhuan");
        userService.insert(user);
    }
}
