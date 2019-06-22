package com.yh.boot.redis.cluster.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 *
 * @author yanhuan
 */
@Component
public class RedisSessionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RedisSessionInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String id = httpServletRequest.getParameter("userId");

        if (session.getAttribute("loginUserId_" + id) != null) {
            try {
                String userId = redisTemplate.opsForValue().get("loginUserId_" + id).toString();
                if (userId != null && userId.equalsIgnoreCase(session.getId())) {
                    return true;
                }
            } catch (Exception e) {
                logger.error("RedisSessionInteceptor preHandle error:{}", e);
            }
        }
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");

        try {
            httpServletResponse.getWriter().print("用户未登录");
        } catch (Exception e) {
            logger.error("RedisSessionInteceptor preHandle error:{}", e);
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
