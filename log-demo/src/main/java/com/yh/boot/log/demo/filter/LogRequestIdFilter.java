package com.yh.boot.log.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;

public class LogRequestIdFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LogRequestIdFilter.class);

    /**
     * 标识出一次请求
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
