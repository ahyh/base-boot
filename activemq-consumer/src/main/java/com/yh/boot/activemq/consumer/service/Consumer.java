package com.yh.boot.activemq.consumer.service;

import org.apache.activemq.Message;

/**
 * 消费接口
 *
 * @author yanhuan
 */
public interface Consumer {

    void consume(Message message);

}
