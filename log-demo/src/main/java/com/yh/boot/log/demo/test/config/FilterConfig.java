package com.yh.boot.log.demo.test.config;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.yh.boot.log.demo.filter.LogRequestIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 添加过滤器
 *
 * @author yanhuan
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean requestIdFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new LogRequestIdFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * 注册自定义的Filter
     * 过滤所有的请求
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MDCInsertingServletFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.setOrder(2);
        return registrationBean;
    }


}
