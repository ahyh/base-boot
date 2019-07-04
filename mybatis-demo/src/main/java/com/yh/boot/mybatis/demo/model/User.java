package com.yh.boot.mybatis.demo.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user表映射对象
 *
 * @author huanyan2
 */
@Data
@Table(name = "user")
public class User extends BaseDomain{

    @Id
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Byte userType;

    private Byte userStatus;
}
