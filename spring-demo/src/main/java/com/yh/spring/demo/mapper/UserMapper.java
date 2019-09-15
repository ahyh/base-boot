package com.yh.spring.demo.mapper;

import com.yh.spring.demo.pojo.User;
import com.yh.spring.demo.pojo.UserQuery;
import com.yh.spring.demo.proxy.redis.annotation.RedisMapper;
import com.yh.spring.demo.proxy.redis.annotation.RedisMethod;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mybatis映射接口
 *
 * @author yanhuan
 */
@RedisMapper
public interface UserMapper {

    @RedisMethod
    User getUserById(Long id);

    Integer insert(User User);

    @RedisMethod
    List<User> findUserList(UserQuery userQuery);

    @RedisMethod
    Integer deleteById(Long id);

    Integer updateUser(User User);

    Integer insertBatch(@Param("list") List<User> userList);
}
