package com.yh.boot.redis.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot集成Redis集群、SpringBoot+Redis实现Session共享的主启动类
 *
 * @author yanhuan
 */
@SpringBootApplication
public class RedisClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisClusterApplication.class, args);
    }

}
