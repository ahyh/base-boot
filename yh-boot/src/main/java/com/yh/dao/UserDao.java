package com.yh.dao;

import com.yh.pojo.User;
import com.yh.pojo.condition.UserCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 增删改查Dao
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
