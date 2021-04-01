package com.yh.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.yh.mybatis.plus.dao")
public class MyBatisPlusDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusDemoApp.class, args);
    }
}
