package com.yh.boot.activemq.producer.service.impl;

import com.yh.boot.activemq.producer.service.AsyncProducer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

/**
 * 异步发送消息的ActiveMQ生产者
 *
 * @author yanhuan
 */
@Component
public class AsyncActiveMqProducer implements AsyncProducer {

    private static final Logger logger = LoggerFactory.getLogger(AsyncActiveMqProducer.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Override
    public boolean asyncSendQueue(String queueName, String message) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory = (ActiveMQConnectionFactory) connectionFactory;
        Connection connection = activeMQConnectionFactory.createConnection();
        Session session = connection.createSession(false, DeliveryMode.PERSISTENT);
        Queue queue = session.createQueue(queueName);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);
        //异步回调，onException可以获取到投递失败的消息
        producer.send(session.createTextMessage(message), new AsyncCallback() {
            @Override
            public void onSuccess() {
                logger.info("Success Send Message to Queue:{}", message);
            }

            @Override
            public void onException(JMSException e) {
                logger.error("Failed Send Message to Queue:{}", message);
            }
        });
        return true;
    }

    @Override
    public boolean asyncSendTopic(String topicName, String message) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory = (ActiveMQConnectionFactory) connectionFactory;
        Connection connection = activeMQConnectionFactory.createConnection();
        Session session = connection.createSession(false, DeliveryMode.PERSISTENT);
        Topic topic = session.createTopic(topicName);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(topic);
        //异步回调，onException可以获取到投递失败的消息
        producer.send(session.createTextMessage(message), new AsyncCallback() {
            @Override
            public void onSuccess() {
                logger.info("Success Send Message to Topic:{}", message);
            }

            @Override
            public void onException(JMSException e) {
                logger.error("Failed Send Message to Topic:{}", message);
            }
        });
        return true;
    }
}
