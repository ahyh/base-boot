package com.yh.boot.mybatis.demo.service.impl;

import com.yh.boot.mybatis.demo.dao.UserDao;
import com.yh.boot.mybatis.demo.model.User;
import com.yh.boot.mybatis.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * UserService实现类
 *
 * @author yanhuan
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> findUserListByName(String username) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return userDao.selectByExample(example);
    }

    @Override
    public List<User> findUserListByCreateTimeBetween(Date start, Date end) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("createTime", start, end);
        return userDao.selectByExample(example);
    }

    @Override
    public int updateByName(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        user.setUpdateTime(new Date());
        user.setUpdateUser("updateUser");
        return userDao.updateByExampleSelective(user, example);
    }


}
