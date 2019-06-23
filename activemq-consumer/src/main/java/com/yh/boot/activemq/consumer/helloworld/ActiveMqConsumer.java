package com.yh.boot.activemq.consumer.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ActiveMq消费者Demo，学习源码使用
 *
 * @author yanhuan
 */
public class ActiveMqConsumer {

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
        MessageConsumer consumer = session.createConsumer(queue);
        //6-消费消息

        /**
         * 1-通过receive方法拉取消息消费
        while (true) {
            TextMessage message = (TextMessage) consumer.receive();
            if (message != null) {
                System.out.println("Consumer receive message:" + message.getText());
            } else {
                break;
            }
        }
         **/

        /**
         * 2-通过Listener的方式监听
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message != null && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("Listener success:" + textMessage.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
         */



        //7-关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
