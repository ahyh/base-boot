package com.yh.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义Filter
 *
 * @author yanhuan
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter process");
        String remoteAddr = servletRequest.getRemoteAddr();
        if (StringUtils.isBlank(remoteAddr)) {
            servletResponse.getWriter().write("Invaild request");
            return;
        }
        if (!remoteAddr.startsWith("192.168.")) {
            servletResponse.getWriter().write("Invaild request");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter destroy...");
    }
}
