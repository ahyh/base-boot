package com.yh.spring.demo.manager.impl;

import com.yh.spring.demo.annotations.MethodAnnotation;
import com.yh.spring.demo.manager.UserSalaryManager;
import com.yh.spring.demo.mapper.SalaryMapper;
import com.yh.spring.demo.mapper.UserMapper;
import com.yh.spring.demo.pojo.Salary;
import com.yh.spring.demo.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 测试Spring事务
 * 测试Spring事务提交和finally中方法执行顺序，断点位置
 * DataSourceTransactionManager.doCommit，这个方法提交事务
 *
 * @author yanhuan
 */
@Component
public class UserSalaryManagerImpl implements UserSalaryManager {

    private static final Logger logger = LoggerFactory.getLogger(UserSalaryManagerImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @MethodAnnotation
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean insertUserWithSalary(User user, Salary salary) {
        try {
            Integer insert = userMapper.insert(user);
            if (insert != 1) {
                throw new RuntimeException("insert user failed");
            }
            Integer insert1 = salaryMapper.insert(salary);
            if (insert1 != 1) {
                throw new RuntimeException("insert salary failed");
            }
        } catch (Exception e) {
            logger.error("insert user with salary failed,error:{}", e);
            throw new RuntimeException("insert user with salary failed");
        } finally {
            System.out.println("Transactional finally");
        }
        System.out.println("Transactional return");
        return true;
    }
}
