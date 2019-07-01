package com.yh.boot.fastdfs.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.yh.boot.fastdfs")
public class FastDfsConfig {

    private String fastDfsUrl;

    public String getFastDfsUrl() {
        return fastDfsUrl;
    }

    public void setFastDfsUrl(String fastDfsUrl) {
        this.fastDfsUrl = fastDfsUrl;
    }
}
