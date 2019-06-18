package com.yh.boot.mq.rocket.consumer.mq.impl;

import com.yh.boot.mq.rocket.consumer.config.mq.RocketMqConsumerConfig;
import com.yh.boot.mq.rocket.consumer.mq.MqConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConditionalOnProperty(name="rocketmq-consumer-flag",havingValue = "true")
public class RocketMqConsumer implements MqConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RocketMqConsumer.class);

    @Autowired
    private RocketMqConsumerConfig rocketMqConsumerConfig;

    @Autowired
    private RocketMqListener rocketMqListener;

    @Bean
    @Override
    public DefaultMQPushConsumer consume() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMqConsumerConfig.getConsumerGroup());
        consumer.setNamesrvAddr(rocketMqConsumerConfig.getNameServer());
        consumer.setConsumeThreadMin(rocketMqConsumerConfig.getConsumerThreadMin());
        consumer.setConsumeThreadMax(rocketMqConsumerConfig.getConsumerThreadMax());
        consumer.setVipChannelEnabled(false);
        consumer.registerMessageListener(rocketMqListener);
        try {
            Map<String, String> topicConsumerMap = rocketMqConsumerConfig.getTopicConsumerMap();
            for (Map.Entry<String, String> entry : topicConsumerMap.entrySet()) {
                consumer.subscribe(entry.getKey(), "*");
            }
            consumer.start();
        } catch (Exception e) {
            logger.error("RocketMqConsumer consume error:{}", e);
        }
        return consumer;
    }

}
