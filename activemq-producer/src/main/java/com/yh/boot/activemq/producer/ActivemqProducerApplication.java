package com.yh.boot.activemq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * ActiveMq的主启动类
 *
 * @author yanhuan
 */
@EnableJms
@SpringBootApplication
public class ActivemqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqProducerApplication.class, args);
    }

}
