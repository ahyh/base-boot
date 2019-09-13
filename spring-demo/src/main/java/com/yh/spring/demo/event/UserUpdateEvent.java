package com.yh.spring.demo.event;

import lombok.Data;

/**
 * 用户更新事件
 *
 * @author yanhuan
 */
@Data
public class UserUpdateEvent {

    private Long id;

    private String username;

    private String fieldName;

    private Object oldValue;

    private Object newValue;

}
