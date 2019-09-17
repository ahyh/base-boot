package com.yh.spring.demo.manager;

import com.yh.spring.demo.pojo.Salary;
import com.yh.spring.demo.pojo.User;

/**
 * 事务相关接口
 *
 * @author yanhuan
 */
public interface UserSalaryManager {

    boolean insertUserWithSalary(User user, Salary salary);
}
