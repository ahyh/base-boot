package com.yh.boot.redis.cluster.controller;

import com.yh.boot.redis.cluster.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 测试SpringBoot+Redis实现Session共享
 *
 * @author yanhuan
 */
@Api("测试Session共享的Controller")
@Controller
@RequestMapping("/user")
public class SessionShareController {

    private static final Logger logger = LoggerFactory.getLogger(SessionShareController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(notes = "登录方法", value = "登录方法实现Session共享")
    @GetMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, @RequestParam String id,@RequestParam String name) {
        try {
            if (id != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loginUserId_" + id, id);
                redisTemplate.opsForValue().set("loginUserId_" + id, session.getId());
                return "登录成功";
            }
            return "用户信息不完整";
        } catch (Exception e) {
            logger.error("SessionShareController login error:{}", e);
        }
        return "登录失败";
    }

    @ApiOperation(notes = "获取登录的信息", value = "获取登录的用户信息")
    @GetMapping("/get")
    @ResponseBody
    public User user(@RequestParam String userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("loginUserId_" + userId);
        User user = new User();
        user.setId(id);
        return user;
    }

    @ApiOperation(notes = "登出", value = "登出")
    @PostMapping("/logout/{userId}")
    @ResponseBody
    public String logout(@PathVariable("userId") String userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUserId_" + userId);
        redisTemplate.opsForValue().getOperations().delete("loginUserId_" + userId);
        return "成功";
    }
}
