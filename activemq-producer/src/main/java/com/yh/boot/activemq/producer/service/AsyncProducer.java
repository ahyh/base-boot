package com.yh.boot.activemq.producer.service;

/**
 * 异步发送消息
 *
 * @author yanhuan
 */
public interface AsyncProducer {

    /**
     * 异步发送消息到Queue
     *
     * @param queueName Queue的名称
     * @param message   消息内容
     * @return 是否完成发送动作，异步发送消息可能会有消息丢失，使用AsyncCallback接口进行回调，onException方法可以监听到失败的消息
     * @throws Exception JmsException异常
     */
    boolean asyncSendQueue(String queueName, String message) throws Exception;

    /**
     * 异步发送消息到Topic
     *
     * @param topicName Topic名称
     * @param message   消息内容
     * @return 是否完成发送动作，异步发送成功后会回调AsyncCallback的onSuccess方法，否则调用onException方法
     * @throws Exception JmsException异常
     */
    boolean asyncSendTopic(String topicName, String message) throws Exception;
}
