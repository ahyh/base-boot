package com.yh.boot.mq.rocket.producer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * RocketMq消息发送主类
 *
 * @author yanhuan
 */
@EnableAspectJAutoProxy
@MapperScan(value = "com.yh.boot.mq.rocket.producer.dao")
@SpringBootApplication
public class RocketmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqProducerApplication.class, args);
    }

}
