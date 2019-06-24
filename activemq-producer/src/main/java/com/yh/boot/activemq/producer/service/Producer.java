package com.yh.boot.activemq.producer.service;

/**
 * 消息生产者接口
 *
 * @author yanhuan
 */
public interface Producer {

    /**
     * 发送消息到队列
     *
     * @param queueName 队列名称
     * @return 是否发送成功
     */
    boolean send2Queue(String queueName, Object object);

    /**
     * 发送消息到主题
     *
     * @param topicName 主题名称
     * @return 是否发送成功
     */
    boolean send2Topic(String topicName, Object object);
}
