package com.yh.boot.mq.rocket.producer.config.mq;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ生产者配置类
 *
 * @author yanhuan
 */
@Configuration
@ConfigurationProperties(prefix = "rocketmq.producer")
@ConditionalOnProperty(name = "enable-rocketmq-flag", havingValue = "true")
public class RocketMqProdConfig {

    /**
     * NameServer
     */
    private String nameServer;

    /**
     * 生产组名
     */
    private String producerGroup;

    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }
}
