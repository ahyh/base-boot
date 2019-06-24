package com.yh.boot.activemq.producer.service.impl;

import com.yh.boot.activemq.producer.service.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ActiveMq消息生产实现类
 *
 * @author yanhuan
 */
@Component
public class ActiveMqProducer implements Producer {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMqProducer.class);

    private static final Map<String, Queue> QUEUE_MAP = new ConcurrentHashMap<>();

    private static final Map<String, Topic> TOPIC_MAP = new ConcurrentHashMap<>();

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public boolean send2Queue(String queueName, Object object) {
        try {
            Queue queue = QUEUE_MAP.get(queueName);
            if (queue == null) {
                Queue q = new ActiveMQQueue(queueName);
                jmsMessagingTemplate.convertAndSend(q, object);
                QUEUE_MAP.put(queueName, q);
            } else {
                jmsMessagingTemplate.convertAndSend(queue, object);
            }
            return true;
        } catch (Exception e) {
            logger.error("ActiveMqProducer send2Queue error:{}", e);
        }
        return false;
    }

    @Override
    public boolean send2Topic(String topicName, Object object) {
        try {
            Topic topic = TOPIC_MAP.get(topicName);
            if (topic == null) {
                ActiveMQTopic activeMQTopic = new ActiveMQTopic(topicName);
                jmsMessagingTemplate.convertAndSend(activeMQTopic, object);
                TOPIC_MAP.put(topicName, activeMQTopic);
            } else {
                jmsMessagingTemplate.convertAndSend(topic, object);
            }
            return true;
        } catch (Exception e) {
            logger.error("ActiveMqProducer send2Topic error:{}", e);
        }
        return false;
    }
}
