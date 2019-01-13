package com.yh.controller;

import com.yh.pojo.User;
import com.yh.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * user控制器
 *
 * @author yanhuan
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/user/add")
    public Integer addUser(@RequestBody User user) {
        return userService.insert(user);
    }

    @ApiOperation(value = "根据id获取user对象")
    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
}
