package com.yh.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置Druid数据源
 *
 * @author yanhuan
 */
@Configuration
public class DruidDataSourceConfig {

    /**
     * 配置Druid数据源
     *
     * @return 向容器中注入Druid数据源
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid数据源的监控
     *
     * @return 向容器中注入一个Druid数据源监控组件
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParamsMap = new HashMap<>(4);
        initParamsMap.put("loginUsername", "admin");
        initParamsMap.put("loginPassword", "123456");
        //默认允许所有的访问
        initParamsMap.put("allow", "");
        initParamsMap.put("deny", "192.168.15.21");
        bean.setInitParameters(initParamsMap);
        return bean;
    }

    /**
     * 配置Druid数据源的监控Filter
     *
     * @return 向容器中注入一个Druid数据源监控组件Filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParamsMap = new HashMap<>();
        initParamsMap.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParamsMap);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
