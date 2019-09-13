package com.yh.spring.demo.service.impl;

import com.yh.spring.demo.event.UserAddEvent;
import com.yh.spring.demo.event.UserUpdateEvent;
import com.yh.spring.demo.service.UserEventPublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 事件发布服务
 *
 * @author yanhuan
 */
@Service
public class UserEventPublishServiceImpl implements UserEventPublishService, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(UserEventPublishServiceImpl.class);

    private ApplicationContext applicationContext;

    @Override
    public boolean publishUserAddEvent(UserAddEvent userAddEvent) {
        try {
            applicationContext.publishEvent(userAddEvent);
            return true;
        } catch (Exception e) {
            logger.error("UserEventPublishServiceImpl publishUserAddEvent error:{}", e);
        }
        return false;
    }

    @Override
    public boolean publishUserUpdateEvent(UserUpdateEvent userUpdateEvent) {
        try {
            applicationContext.publishEvent(userUpdateEvent);
            return true;
        } catch (Exception e) {
            logger.error("UserEventPublishServiceImpl publishUserAddEvent error:{}", e);
        }
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
