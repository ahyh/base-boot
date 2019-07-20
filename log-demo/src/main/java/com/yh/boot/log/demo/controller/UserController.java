package com.yh.boot.log.demo.controller;

import com.yh.boot.log.demo.domain.Result;
import com.yh.boot.log.demo.domain.User;
import com.yh.boot.log.demo.domain.UserCondition;
import com.yh.boot.log.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
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
            result.setMessage("failed");
            logger.error("UserController findAllUsers error:{}", e);
        }
        logger.info("UserController findAllUsers end");
        return result;
    }
}
