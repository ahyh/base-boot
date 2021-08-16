package com.yh.security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @GetMapping(value = "security/hello")
    public String helloSecurity() {
        return "hello";
    }

    /**
     * 测试不需要进行认证的情况
     */
    @GetMapping(value = "user/hello")
    public String userHello() {
        return "user hello";
    }
}
