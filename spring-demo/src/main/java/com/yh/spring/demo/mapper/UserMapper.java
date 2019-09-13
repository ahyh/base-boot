package com.yh.spring.demo.mapper;

import com.yh.spring.demo.pojo.User;
import com.yh.spring.demo.pojo.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mybatis映射接口
 *
 * @author yanhuan
 */
public interface UserMapper {

    User getUserById(Long id);

    Integer insert(User User);

    List<User> findUserList(UserQuery userQuery);

    Integer deleteById(Long id);

    Integer updateUser(User User);

    Integer insertBatch(@Param("list") List<User> userList);
}
