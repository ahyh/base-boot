package com.yh.boot.boot.mail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * JavaEmailSender配置
 *
 * @author yanhuan
 */
@Configuration
public class EmailSenderConfig {

    @Bean
    public JavaMailSender javaMailSender(EmailConfig emailConfig){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailConfig.getHost());
        javaMailSender.setPort(emailConfig.getPort());
        javaMailSender.setUsername(emailConfig.getUsername());
        javaMailSender.setPassword(emailConfig.getPassword());
        javaMailSender.setProtocol(emailConfig.getProtocol());
        javaMailSender.setJavaMailProperties(emailConfig.getProperties());
        return javaMailSender;
    }
}
