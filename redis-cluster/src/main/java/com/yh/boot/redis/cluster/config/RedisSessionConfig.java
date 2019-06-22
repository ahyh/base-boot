package com.yh.boot.redis.cluster.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Redis实现Session共享配置类
 *
 * @author yanhuan
 */
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300)
@Configuration
public class RedisSessionConfig {

    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        //让springSession不再执行config命令
        return ConfigureRedisAction.NO_OP;
    }
}
