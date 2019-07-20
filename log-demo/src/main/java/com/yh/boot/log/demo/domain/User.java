package com.yh.boot.log.demo.domain;

import lombok.Data;

/**
 * User表映射对象
 *
 * @author yanhuan
 */
@Data
public class User extends BaseDomain {

    private String username;

    private String password;

    private String phone;

    private String email;

    private Integer userType;

    private Integer userStatus;
}
