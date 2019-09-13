package com.yh.spring.demo.listener;

import com.alibaba.fastjson.JSON;
import com.yh.spring.demo.event.UserAddEvent;
import com.yh.spring.demo.event.UserUpdateEvent;
import com.yh.spring.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * UserEvent相关服务
 *
 * @author yanhuan
 */
@Component
public class UserEventListener {

    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @Resource
    private UserMapper userMapper;

    @EventListener(UserAddEvent.class)
    public void userAddEventListener(UserAddEvent userAddEvent) {
        logger.info("UserEventListener userAddEventListener error:{}", JSON.toJSONString(userAddEvent));
    }

    @EventListener(UserUpdateEvent.class)
    public void userUpdateEventListener(UserUpdateEvent userUpdateEvent) {
        logger.info("UserEventListener userUpdateEventListener error:{}", JSON.toJSONString(userUpdateEvent));
    }
}
