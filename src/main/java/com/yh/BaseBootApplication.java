package com.yh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.yh.dao")
@SpringBootApplication
public class BaseBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseBootApplication.class);
    }
}
