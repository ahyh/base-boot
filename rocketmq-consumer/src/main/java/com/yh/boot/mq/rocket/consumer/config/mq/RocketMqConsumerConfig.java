package com.yh.boot.mq.rocket.consumer.config.mq;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * RocketMq消费者配置类
 *
 * @author yanhuan
 */
@Configuration
@ConfigurationProperties(prefix = "rocketmq.consumer")
@ConditionalOnProperty(name = "rocketmq-consumer-flag", havingValue = "true")
public class RocketMqConsumerConfig {


    private String nameServer;

    /**
     * 消费组名
     */
    private String consumerGroup;

    /**
     * 最少消费线程数
     */
    private Integer consumerThreadMin;

    /**
     * 最大消费线程数
     */
    private Integer consumerThreadMax;

    /**
     * 主题-消费者集合
     */
    private Map<String, String> topicConsumerMap;

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public Integer getConsumerThreadMin() {
        return consumerThreadMin;
    }

    public void setConsumerThreadMin(Integer consumerThreadMin) {
        this.consumerThreadMin = consumerThreadMin;
    }

    public Integer getConsumerThreadMax() {
        return consumerThreadMax;
    }

    public void setConsumerThreadMax(Integer consumerThreadMax) {
        this.consumerThreadMax = consumerThreadMax;
    }

    public Map<String, String> getTopicConsumerMap() {
        return topicConsumerMap;
    }

    public void setTopicConsumerMap(Map<String, String> topicConsumerMap) {
        this.topicConsumerMap = topicConsumerMap;
    }
}
