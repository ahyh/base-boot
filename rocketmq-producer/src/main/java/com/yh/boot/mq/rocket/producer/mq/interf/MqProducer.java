package com.yh.boot.mq.rocket.producer.mq.interf;

/**
 * MQ的生产者
 *
 * @author yanhuan
 */
public interface MqProducer {

    /**
     * 发送消息方法
     *
     * @param topic 消息的主题
     * @param tags  消息的标签，后续消费的时候可以使用这个值进行过滤
     * @param keys  关键词
     * @param body  消息内容
     * @return 是否发送消息成功
     */
    boolean send(String topic, String tags, String keys, String body);
}
