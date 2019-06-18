package com.yh.boot.mq.rocket.consumer.mq;

import com.alibaba.fastjson.JSON;

/**
 * RocketMq消息处理接口，具体的消费者需要实现这个接口
 *
 * @param <T>
 * @author yanhuan
 */
public interface MessageProcessor<T> {

    boolean handleMessage(T message);

    Class<T> getClazz();

    default T transferMessage(String messageStr) {
        return JSON.parseObject(messageStr, getClazz());
    }
}
