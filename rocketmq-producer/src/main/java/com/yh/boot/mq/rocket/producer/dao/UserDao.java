package com.yh.boot.mq.rocket.producer.dao;

import com.yh.boot.mq.rocket.producer.model.User;
import com.yh.boot.mq.rocket.producer.model.condition.UserCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user的增删改查
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
