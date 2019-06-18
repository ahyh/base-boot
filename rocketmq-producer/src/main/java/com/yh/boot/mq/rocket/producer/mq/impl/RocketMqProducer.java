package com.yh.boot.mq.rocket.producer.mq.impl;

import com.yh.boot.mq.rocket.producer.config.mq.RocketMqProdConfig;
import com.yh.boot.mq.rocket.producer.mq.interf.MqProducer;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * RocketMQ生产者逻辑
 *
 * @author yanhuan
 */
@Component
@ConditionalOnProperty(name = "enable-rocketmq-flag", havingValue = "true")
public class RocketMqProducer implements MqProducer {

    private static final Logger logger = LoggerFactory.getLogger(RocketMqProducer.class);

    @Autowired
    private RocketMqProdConfig rocketMqProdConfig;

    private DefaultMQProducer rocketMqProducer;

    /**
     * 启动Rocket生产者
     */
    @PostConstruct
    public void defaultMQProducer() {
        rocketMqProducer = new DefaultMQProducer(rocketMqProdConfig.getProducerGroup());
        rocketMqProducer.setNamesrvAddr(rocketMqProdConfig.getNameServer());
        rocketMqProducer.setVipChannelEnabled(false);
        try {
            rocketMqProducer.start();
            logger.info("RocketMqProducer defaultMQProducer begin...");
        } catch (Exception e) {
            logger.error("RocketMqProducer defaultMQProducer error:{}", e);
        }
    }

    /**
     * 发送消息方法
     *
     * @param topic 消息的主题
     * @param tags  消息的标签，后续消费的时候可以使用这个值进行过滤
     * @param keys  关键词
     * @param body  消息内容
     * @return 是否发送成功
     */
    @Override
    public boolean send(String topic, String tags, String keys, String body) {
        try {
            Message message = new Message(topic, tags, keys, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            StopWatch watch = new StopWatch();
            watch.start();
            SendResult sendResult = rocketMqProducer.send(message);
            watch.stop();
            if (sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("RocketMqProducer send false topic:{},tags:{},body:{},error:{}", topic, tags, tags, e);
            return false;
        }
    }

}
