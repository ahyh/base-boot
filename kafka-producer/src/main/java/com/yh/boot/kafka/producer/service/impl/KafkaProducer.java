package com.yh.boot.kafka.producer.service.impl;

import com.yh.boot.kafka.producer.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Kafka发送消息
 *
 * @author yanhuan
 */
@Component
public class KafkaProducer implements Producer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送消息方法，发送成功调用SuccessCallback中方法，否则调用FailureCallback中方法
     * 可以获取到发送失败的消息
     *
     * @param topicName 消息的主题名称
     * @param message   消息内容
     * @return 是否投递成功
     */
    @Override
    public boolean send(String topicName, Object message) {
        ListenableFuture send = kafkaTemplate.send(topicName, message);
        send.addCallback(o -> logger.info("Send Message To Kafka success,message:{}", message), throwable -> logger.error("Send Message To Kafka failed,message:{}", message));
        return true;
    }

}
