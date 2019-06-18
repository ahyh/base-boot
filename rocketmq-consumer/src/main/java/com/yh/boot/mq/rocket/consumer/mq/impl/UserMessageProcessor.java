package com.yh.boot.mq.rocket.consumer.mq.impl;

import com.alibaba.fastjson.JSON;
import com.yh.boot.mq.rocket.consumer.model.User;
import com.yh.boot.mq.rocket.consumer.mq.MessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserMessageProcessor implements MessageProcessor<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserMessageProcessor.class);

    @Override
    public boolean handleMessage(User message) {
        logger.info("UserMessageProcessor handleMessage message:{}", JSON.toJSONString(message));
        return true;
    }

    @Override
    public Class<User> getClazz() {
        return User.class;
    }
}
