package com.yh.security.demo.mapper;

import com.yh.security.demo.domain.User;
import com.yh.security.demo.domain.UserQueryCondition;
import com.yh.security.demo.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User getUserById(Long id);

    User getUserByUsername(String username);

    List<UserRole> findUserRolesByUserId(Long userId);

    List<User> findUserListByQuery(UserQueryCondition queryCondition);
}
