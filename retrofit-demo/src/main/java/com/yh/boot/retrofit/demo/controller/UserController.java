package com.yh.boot.retrofit.demo.controller;

import com.yh.boot.retrofit.demo.pojo.User;
import com.yh.boot.retrofit.demo.pojo.UserCollection;
import com.yh.boot.retrofit.demo.pojo.UserQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * HTTP接口，提供给Retrofit调用
 *
 * @author yanhuan
 */
@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) throws InterruptedException {
        List<User> userList = UserCollection.USER_LIST.stream()
                .filter(user -> user.getId().equals(id)).collect(Collectors.toList());
        TimeUnit.SECONDS.sleep(6);
        return userList.get(0);
    }

    @GetMapping("/user/all")
    public List<User> getAllUser() {
        return UserCollection.USER_LIST;
    }

    @PostMapping("/user/query")
    public List<User> queryUserList(@RequestBody UserQuery userQuery) {
        List<User> userList = UserCollection.USER_LIST.stream()
                .filter(user -> user.getName().equals(userQuery.getName()))
                .filter(user -> user.getAge().equals(userQuery.getAge()))
                .collect(Collectors.toList());
        return userList;
    }
}
