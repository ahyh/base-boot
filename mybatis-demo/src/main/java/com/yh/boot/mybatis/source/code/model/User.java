package com.yh.boot.mybatis.source.code.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Byte userType;

    private Byte userStatus;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Byte isDelete;
}
