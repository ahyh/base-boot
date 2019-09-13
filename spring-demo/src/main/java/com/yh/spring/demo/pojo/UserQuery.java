package com.yh.spring.demo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * User-查询条件
 */
@Data
public class UserQuery implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Integer userType;

    private Integer userStatus;
}
