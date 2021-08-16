package com.yh.security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class AuthTestController {

    private static final Logger logger = LoggerFactory.getLogger(AuthTestController.class);

    /**
     * 当前登录的user，有对应的Role就可以访问
     * 启动类上加注解 @EnableGlobalMethodSecurity(securedEnabled = true)
     */
    @Secured({"ROLE_secured", "ROLE_admin"})
    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }

    /**
     * 当前登录的user必须有admin/user的权限才能访问
     * 启动类上加注解 @EnableGlobalMethodSecurity(prePostEnabled = true)
     */
    @PreAuthorize("hasAnyAuthority('admins')")
    @GetMapping("/preauth")
    public String preAuth() {
        return "preAuth";
    }

    /**
     * 方法执行之后再校验
     * 启动类上加注解 @EnableGlobalMethodSecurity(prePostEnabled = true)
     */
    @PostAuthorize("hasAnyAuthority('admins')")
    @GetMapping("/postauth")
    public String postAuth() {
        return "postAuth";
    }
}
