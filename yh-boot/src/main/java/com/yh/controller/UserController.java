package com.yh.controller;

import com.yh.pojo.User;
import com.yh.pojo.condition.UserCondition;
import com.yh.service.HelloService;
import com.yh.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * user控制器
 *
 * @author yanhuan
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private HelloService helloService;

    @PostMapping(value = "/user/add")
    public Integer addUser(@RequestBody User user) {
        return userService.insert(user);
    }

    @ApiOperation(value = "根据id获取user对象")
    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @ApiOperation(value = "测试自定义场景启动器")
    @GetMapping(value = "username/{username}")
    public User getUserByName(@PathVariable("username") String username) {
        UserCondition condition = new UserCondition();
        condition.setUsername(username);
        List<User> userList = userService.findUserList(condition);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }
}
