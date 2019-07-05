package com.yh.boot.elasticsearch.demo.service.impl;

import com.yh.boot.elasticsearch.demo.esdao.UserRepository;
import com.yh.boot.elasticsearch.demo.model.User;
import com.yh.boot.elasticsearch.demo.service.UserService;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ES操作User文档的服务
 *
 * @author yanhuan
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public int insert(User user) {
        userRepository.save(user);
        return 1;
    }

    @Override
    public int insertBatch(List<User> userList) {
        Iterable<User> userIterable = () -> userList.iterator();
        userRepository.saveAll(userIterable);
        return userList.size();
    }

    @Override
    public List<User> findAll() {
        Iterable<User> it = userRepository.findAll();
        List<User> list = new ArrayList<>();
        it.forEach(user -> list.add(user));
        return list;
    }

    @Override
    public List<User> findUserByUsername(String username) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(0, 100))
                .withQuery(QueryBuilders.matchQuery("username", username))
                .build();
        Page<User> page = userRepository.search(searchQuery);
        Iterator<User> iterator = page.iterator();
        List<User> list = new ArrayList<>();
        iterator.forEachRemaining(user -> list.add(user));
        return list;
    }

    @Override
    public List<User> findUserByCreateTimeBetween(String start, String end) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(0, 100))
                .withQuery(QueryBuilders.rangeQuery("createTime").gt(start))
                .build();
        Page<User> page = userRepository.search(searchQuery);
        Iterator<User> iterator = page.iterator();
        List<User> list = new ArrayList<>();
        iterator.forEachRemaining(user -> list.add(user));
        return list;
    }


}
