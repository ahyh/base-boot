package com.yh.boot.activemq.consumer.config;

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

    @Bean
    public JmsListenerContainerFactory topicListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory topicFactory = new DefaultJmsListenerContainerFactory();
        topicFactory.setPubSubDomain(true);
        topicFactory.setConnectionFactory(connectionFactory);
        return topicFactory;
    }

    @Bean
    public JmsListenerContainerFactory queueListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory topicFactory = new DefaultJmsListenerContainerFactory();
        topicFactory.setPubSubDomain(false);
        topicFactory.setConnectionFactory(connectionFactory);
        return topicFactory;
    }
}
