package com.yh.boot.log.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础pojo，建表必有字段
 *
 * @author yanhuan
 */
@Data
public abstract class BaseDomain implements Serializable {

    private Long id;

    private Integer isDelete;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}
