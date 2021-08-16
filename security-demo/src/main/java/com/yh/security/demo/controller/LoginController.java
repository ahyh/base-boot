package com.yh.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping(value = "user/signin")
    public String userSignin(){
        return "signin";
    }

    @GetMapping(value = "success")
    public String success(){
        return "success";
    }
}
