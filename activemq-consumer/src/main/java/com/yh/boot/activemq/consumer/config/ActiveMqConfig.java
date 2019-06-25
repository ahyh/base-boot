package com.yh.boot.activemq.consumer.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * ActiveMq消费配置类
 *
 * @author yanhuan
 */
@Configuration
public class ActiveMqConfig {

    /**
     * 用于监听持久化的Topic的配置
     */
    @Bean
    public JmsListenerContainerFactory topicListenerContainerFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory topicFactory = new DefaultJmsListenerContainerFactory();
        topicFactory.setPubSubDomain(true);
        topicFactory.setClientId("clientId");
        topicFactory.setSubscriptionDurable(true);
        topicFactory.setConnectionFactory(connectionFactory);
        configurer.configure(topicFactory, connectionFactory);
        return topicFactory;
    }

    /**
     * 用于监听队列的配置
     */
    @Bean
    public JmsListenerContainerFactory queueListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory topicFactory = new DefaultJmsListenerContainerFactory();
        topicFactory.setPubSubDomain(false);
        topicFactory.setConnectionFactory(connectionFactory);
        return topicFactory;
    }
}
