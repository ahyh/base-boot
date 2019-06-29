package com.yh.boot.kafka.producer.service;

/**
 * Kafka生产者接口
 *
 * @author yanhuan
 */
public interface Producer {

    /**
     * 发送消息
     *
     * @param topicName 消息的主题名称
     * @param message   消息内容
     * @return 是否完成投递动作
     */
    boolean send(String topicName, Object message);

}
