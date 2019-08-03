package com.yh.boot.mybatis.source.code.dao;

import com.yh.boot.mybatis.source.code.model.User;
import com.yh.boot.mybatis.source.code.model.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User getUserById(Long id);

    List<User> findUserListByQuery(@Param("userQuery") UserQuery userQuery);

}
