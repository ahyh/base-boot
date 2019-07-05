package com.yh.boot.elasticsearch.demo.test;

import com.yh.boot.elasticsearch.demo.ElasticsearchDemoApplicationTests;
import com.yh.boot.elasticsearch.demo.esdao.UserRepository;
import com.yh.boot.elasticsearch.demo.model.User;
import com.yh.boot.elasticsearch.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserServiceTest extends ElasticsearchDemoApplicationTests {

    private static final List<User> USERS = new ArrayList<>();

    static {
        List<User> users = Arrays.asList(
                new User(UUID.randomUUID().toString(), "aa", "aa", "123456", "aa@163.com", 1, 2, 0, "yanhuan", "2019-07-03 07:00:00", "yanhuan", "2019-07-03 13:00:00"),
                new User(UUID.randomUUID().toString(), "bb", "bb", "123456", "bb@163.com", 1, 2, 0, "yanhuan", "2019-07-03 08:00:00", "yanhuan", "2019-07-03 14:00:00"),
                new User(UUID.randomUUID().toString(), "cc", "cc", "123456", "cc@163.com", 1, 2, 0, "yanhuan", "2019-07-03 09:00:00", "yanhuan", "2019-07-03 15:00:00"),
                new User(UUID.randomUUID().toString(), "dd", "dd", "123456", "dd@163.com", 1, 2, 0, "yanhuan", "2019-07-03 10:00:00", "yanhuan", "2019-07-03 16:00:00"),
                new User(UUID.randomUUID().toString(), "ee", "ee", "123456", "ee@163.com", 1, 2, 0, "yanhuan", "2019-07-03 11:00:00", "yanhuan", "2019-07-03 17:00:00")
        );
        USERS.addAll(users);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        userRepository.save(USERS.get(1));
    }

    @Test
    public void testServiceInsert(){
        int insert = userService.insertBatch(USERS);
        Assert.assertTrue(insert > 1);
    }

    @Test
    public void testFindAll(){
        List<User> all = userService.findAll();
        all.stream().forEach(System.out::println);
    }

    @Test
    public void testFind(){
        List<User> users = userService.findUserByUsername("aa");
        users.stream().forEach(System.out::println);
    }

    @Test
    public void testFindByCreateTimeBetween(){
        String start = "2019-06-01 00:00:00";
        String end = "2019-07-31 23:59:59";
        List<User> users = userService.findUserByCreateTimeBetween(start, end);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void testDeleteAll(){
        userRepository.deleteAll();
    }




}
