package com.yh.boot.activemq.consumer.service.impl;

import com.yh.boot.activemq.consumer.service.Consumer;
import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * ActiveMq队列消费者，监听Queue:${myqueue}
 *
 * @author yanhuan
 */
@Component
public class ActiveMqQueueConsumer implements Consumer {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMqQueueConsumer.class);

    @JmsListener(destination = "${myqueue}",containerFactory = "queueListenerContainerFactory")
    @Override
    public void consume(Message message) {
        logger.info("ActiveMqQueueConsumer consume message:{}", message);
    }
}
