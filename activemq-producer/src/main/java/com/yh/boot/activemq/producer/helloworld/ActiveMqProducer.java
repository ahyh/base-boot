package com.yh.boot.activemq.producer.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ActiveMq消息生产Demo，用于熟悉ActiveMQ生产消息的流程，学习源码
 *
 * @author yanhuan
 */
public class ActiveMqProducer {

    /**
     * ActiveMQ的地址
     */
    public static final String ACTIVE_MQ_URL = "tcp://192.168.0.120:61616";

    public static final String DESTINATION = "Producer-Demo";

    public static void main(String[] args) throws Exception {
        //1-创建ActiveMq的连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVE_MQ_URL);
        //2-创建连接并启动
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3-通过连接创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4-创建队列
        Queue queue = session.createQueue(DESTINATION);
        //5-创建生产者
        MessageProducer producer = session.createProducer(queue);
        //设置消息的持久化，在ActiveMQ宕机后重启，消息仍然存在，保证消息的可靠性，未消费的消息重启后不会丢失，
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //6-发送3条消息
        for (int i = 0; i < 3; i++) {
            //创建TextMessage并发送
            TextMessage textMessage = session.createTextMessage("TextMessage:" + i);
            producer.send(textMessage);
        }
        System.out.println("Message send success");
        //7-关闭资源
        producer.close();
        session.close();
        connection.close();
    }

}
