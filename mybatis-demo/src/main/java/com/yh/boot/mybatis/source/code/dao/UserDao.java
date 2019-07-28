package com.yh.boot.mybatis.source.code.dao;

import com.yh.boot.mybatis.source.code.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Tabel user's DAO
 *
 * @author yanhuan
 */
public interface UserDao {

    User getUserById(Long id);

    Integer insert(User User);

    List<User> findUserList();

    Integer deleteById(Long id);

    Integer updateUser(User User);

    Integer insertBatch(@Param("list") List<User> userList);

}
