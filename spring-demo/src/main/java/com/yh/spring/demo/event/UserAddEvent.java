package com.yh.spring.demo.event;

import lombok.Data;

import java.util.Date;

/**
 * 新增用户事件
 *
 * @author yanhuan
 */
@Data
public class UserAddEvent {

    private String username;

    private String phone;

    private String email;

    private Integer userType;

    private Integer userStatus;

    private Date createTime;
}
