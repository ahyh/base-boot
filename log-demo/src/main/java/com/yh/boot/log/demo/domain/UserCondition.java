package com.yh.boot.log.demo.domain;


import lombok.Data;

/**
 * User查询条件
 *
 * @author yanhuan
 */
@Data
public class UserCondition extends BaseDomain {

    private String username;

    private String password;

    private String phone;

    private String email;

    private Integer userType;

    private Integer userStatus;

}
