package com.yh.boot.mq.rocket.producer.controller;

import com.yh.boot.mq.rocket.producer.model.Result;
import com.yh.boot.mq.rocket.producer.model.User;
import com.yh.boot.mq.rocket.producer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RocketMQ发送消息控制器
 *
 * @author yanhuan
 */
@Api(value = "发送RocketMq控制器")
@RestController(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试写入数据同时发送RocketMQ")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Boolean> addUser(@RequestBody User user) {
        Result result = new Result();
        try {
            Boolean flag = userService.insertAndSend2RocketMq(user);
            result.setCode(1);
            result.setMessage("Send RocketMq success");
            result.setData(flag);
            return result;
        } catch (Exception e) {
            logger.error("UserController addUser error:{}", e);
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setData(false);
            return result;
        }
    }

}
