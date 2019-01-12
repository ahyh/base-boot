package com.yh.controller;

import com.yh.pojo.User;
import com.yh.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Integer addUser(User user) {
        return userService.insert(user);
    }

    @ApiOperation(value = "根据id获取user对象")
    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
}
