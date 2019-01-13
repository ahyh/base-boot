package com.yh.interceptors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 方法调用拦截器
 *
 * @author yanhuan
 */
public class MethodInvocationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MethodInvocationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        //method获取的是GET/POST方法
        String method = httpServletRequest.getMethod();
        String remoteAddr = httpServletRequest.getRemoteAddr();
        //记录一次方法的调用
        if (StringUtils.isNotBlank(requestURI)) {
            logger.info("Method {} invoke by {}", requestURI, remoteAddr);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
