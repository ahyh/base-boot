package com.yh.boot.redis.cluster.config;

import com.yh.boot.redis.cluster.interceptor.RedisSessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 扩展SpringMVC的配置
 *
 * @author yanhuan
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 所有的WebMvcConfigurerAdapter组件都会一起起作用
     */
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(RedisSessionInterceptor redisSessionInterceptor) {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(redisSessionInterceptor).addPathPatterns("/**")
                        .excludePathPatterns("/", "/user/login", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
            }
        };
        return adapter;
    }


}
