package com.yh.boot.mq.rocket.consumer.mq.impl;

import com.yh.boot.mq.rocket.consumer.config.mq.RocketMqConsumerConfig;
import com.yh.boot.mq.rocket.consumer.mq.MessageProcessor;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RocketMQ的监控者
 *
 * @author huanyan
 */
@Component
@ConditionalOnProperty(name = "rocketmq-consumer-flag", havingValue = "true")
public class RocketMqListener implements MessageListenerConcurrently, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(RocketMqListener.class);

    private static final Map<String, MessageProcessor> CONSUMER_MAP = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    @Autowired
    private RocketMqConsumerConfig rocketMqConsumerConfig;

    @PostConstruct
    public void registerConsumer() {
        Map<String, String> topicConsumerMap = rocketMqConsumerConfig.getTopicConsumerMap();
        for (Map.Entry<String, String> entry : topicConsumerMap.entrySet()) {
            CONSUMER_MAP.put(entry.getKey(), (MessageProcessor) applicationContext.getBean(entry.getValue()));
        }
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        MessageExt ext = list.get(0);
        String message = new String(ext.getBody());
        //获取到tag
        String topic = ext.getTopic();
        //根据tag从handleMap里获取到我们的处理类
        MessageProcessor messageProcessor = CONSUMER_MAP.get(topic);
        Object obj = null;
        try {
            //将String类型的message反序列化成对应的对象。
            obj = messageProcessor.transferMessage(message);
        } catch (Exception e) {
            logger.error("RocketMqListener consumeMessage error:{}", e);
        }
        //处理消息
        boolean result = messageProcessor.handleMessage(obj);
        if (!result) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
