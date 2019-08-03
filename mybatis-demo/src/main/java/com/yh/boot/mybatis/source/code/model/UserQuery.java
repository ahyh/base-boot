package com.yh.boot.mybatis.source.code.model;

import lombok.Data;

@Data
public class UserQuery {

    private Long id;

    private String username;

    private String email;

    private String phone;
}
