package com.yh.spring.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO-表映射对象
 *
 * @author yanhuan
 */
@Data
public class User implements Serializable {

    private Long id;

    private Integer isDelete;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Integer userType;

    private Integer userStatus;

}
