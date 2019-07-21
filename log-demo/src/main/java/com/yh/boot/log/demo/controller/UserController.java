package com.yh.boot.log.demo.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.yh.boot.log.demo.domain.Result;
import com.yh.boot.log.demo.domain.User;
import com.yh.boot.log.demo.domain.UserCondition;
import com.yh.boot.log.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserController控制器
 *
 * @author yanhuan
 */
@Api("用户控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @ApiOperation(value = "查询所有用户", notes = "findAllUsers")
    @GetMapping(value = "/all")
    public Result findAllUsers() {
        logger.info("UserController findAllUsers begin");
        Result result = new Result();
        result.setCode(1);
        try {
            List<User> userList = userService.findUserList(new UserCondition());
            result.setMessage("success");
            result.setData(userList);
        } catch (Exception e) {
            result.setCode(0);
            result.setMessage("failed");
            logger.error("UserController findAllUsers error:{}", e);
        }
        logger.info("UserController findAllUsers end");
        return result;
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping(value = "/add")
    public Result addUser(@RequestBody User user) {
        logger.info("UserController addUser user:{}", JSON.toJSON(user));
        Result result = new Result();
        result.setCode(1);
        try {
            //0-参数校验，省略
            Integer insert = userService.insert(user);
            Preconditions.checkArgument(insert == 1, "insert user failed!");
            result.setMessage("Success");
            result.setData(insert);
        } catch (Exception e) {
            logger.error("UserController insert user error:{}", e);
            result.setCode(0);
            result.setMessage("failed");
        }
        logger.info("UserController addUser end");
        return result;
    }
}
