package com.yh.security.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * EnableGlobalMethodSecurity开启基于注解的security权限校验
 *
 * @author huan yan
 */
@EnableGlobalMethodSecurity( prePostEnabled = true, securedEnabled = true)
@MapperScan(value = "com.yh.security.demo.mapper")
@SpringBootApplication
public class SpringSecurityApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApp.class);
    }
}
