package com.yh.boot.log.demo.dao;

import com.yh.boot.log.demo.domain.User;
import com.yh.boot.log.demo.domain.UserCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作数据的DAO，为了记录日志中SQL语句
 *
 * @author yanhuan
 */
public interface UserDao {

    User getUserById(Long id);

    Integer insert(User User);

    List<User> findUserList(UserCondition userCondition);

    Integer deleteById(Long id);

    Integer updateUser(User User);

    Integer insertBatch(@Param("list") List<User> userList);
}
