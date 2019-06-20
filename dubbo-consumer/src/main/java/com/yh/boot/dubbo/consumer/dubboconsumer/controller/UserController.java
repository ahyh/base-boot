package com.yh.boot.dubbo.consumer.dubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yh.model.User;
import com.yh.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController
 *
 * @author yanhuan
 */
@Api("测试一下Dubbo-Consumer的控制器")
@RestController("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserService userService;

    @ApiOperation(notes = "查询User服务", value = "测试dubbo消费")
    @PostMapping("/findAll")
    public List<User> findUserList(@RequestBody User user) {
        return userService.findUserList(user);
    }
}
