package com.yh.config;

import com.yh.properties.HelloProperties;
import com.yh.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 自动配置类
 * EnableConfigurationProperties注解让属性文件生效
 *
 * @author yanhuan
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    @Resource
    private HelloProperties helloProperties;

//    @Bean
//    public HelloService helloService() {
//        HelloService helloService = new HelloService();
//        helloService.setHelloProperties(helloProperties);
//        return helloService;
//    }
}
