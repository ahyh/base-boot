package com.yh.config;

import com.yh.filter.MyFilter;
import com.yh.listener.ApplicationStartListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 服务端组件配置
 *
 * @author yanhuan
 */
@Configuration
public class ServerConfig {

    /**
     * 注册自定义的Filter
     * 过滤所有的请求
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }

    @Bean
    public ApplicationStartListener applicationStartListener(){
        return new ApplicationStartListener();
    }

}
