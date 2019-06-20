package com.yh.boot.dubbo.provider.dao;

import com.yh.model.User;
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

    List<User> findUserList(User user);

    Integer deleteById(Long id);

    Integer updateUser(User User);

    Integer insertBatch(@Param("list") List<User> userList);
}
