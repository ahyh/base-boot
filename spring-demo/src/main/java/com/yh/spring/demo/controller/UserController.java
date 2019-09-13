package com.yh.spring.demo.controller;

import com.yh.spring.demo.common.Result;
import com.yh.spring.demo.pojo.User;
import com.yh.spring.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * User控制器
 *
 * @author yanhuan
 */
@Api("User控制器")
@RestController(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @ApiOperation("根据id查询用户")
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @ApiOperation("添加用户")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result addUser(@RequestBody User user){
        Result result = new Result();
        try {
            userService.insert(user);
            result.setCode(1);
            result.setMessage("Add User success");
            result.setData(true);
        } catch (Exception e) {
            logger.error("UserController addUser error:{}", e);
            result.setCode(0);
            result.setMessage(e.getMessage());
            result.setData(false);
        }
        return result;
    }
}
