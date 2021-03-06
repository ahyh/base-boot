package com.yh.boot.activemq.consumer.service.impl;

import com.yh.boot.activemq.consumer.service.Consumer;
import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * ActiveMq主题消费者
 *
 * @author yanhuan
 */
@Component
public class ActiveMqTopicConsumer implements Consumer {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMqTopicConsumer.class);

    /**
     * 如果返回值不为void，需要设置消费成功的回调
     *
     * @param message 消息
     */
    @JmsListener(id = "${mytopic}", destination = "${mytopic}", subscription = "clientId", containerFactory = "topicListenerContainerFactory")
    @Override
    public void consume(Message message) {
        logger.info("ActiveMqTopicConsumer consume message:{}", message);
    }
}
