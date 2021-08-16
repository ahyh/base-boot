package com.yh.security.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CsrfController {

    private static final Logger logger = LoggerFactory.getLogger(CsrfController.class);

    @GetMapping("to_csrf_token")
    public String toCsrfToken(){
        return "csrf/csrf_token";
    }

    @GetMapping("to_csrf_test")
    public String toCsrfTest(){
        return "csrf/csrf_test";
    }

    @ResponseBody
    @PostMapping("/update_token")
    public String updateToken(){
        return "update";
    }

}
