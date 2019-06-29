package com.yh.boot.kafka.consumer.service.impl;

import com.yh.boot.kafka.consumer.service.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka消费者实现
 *
 * @author yanhuan
 */
@Component
public class MyConsumer implements Consumer {

    public static final Logger logger = LoggerFactory.getLogger(MyConsumer.class);

    @KafkaListener(topics = "${kafka.consumer.topic.test-topic}")
    @Override
    public boolean consume(String message) {
        logger.info("MyConsumer consume message:{}", message);
        return true;
    }
}
