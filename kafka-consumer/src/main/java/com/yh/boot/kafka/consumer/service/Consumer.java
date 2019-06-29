package com.yh.boot.kafka.consumer.service;

/**
 * Kafka消费接口
 *
 * @author yanhuan
 */
public interface Consumer {

    /**
     * Kafka消费者方法
     *
     * @param message Kafka消息内容
     * @return 是否消费成功
     */
    boolean consume(String message);
}
