package com.yh.interceptors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author yanhuan
 */
public class ParamsCheckInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ParamsCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String remoteAddr = httpServletRequest.getRemoteAddr();
        String requestURI = httpServletRequest.getRequestURI();
        if (StringUtils.isNotBlank(remoteAddr)) {
            logger.info("ParamsCheckInterceptor preHandle remoteAddr:{}", remoteAddr);
            return true;
        }
        if (requestURI.contains("user")) {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
