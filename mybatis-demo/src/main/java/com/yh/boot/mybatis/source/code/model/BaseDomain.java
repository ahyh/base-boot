package com.yh.boot.mybatis.source.code.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有的表都会有的字段
 *
 * @author huanyan2
 */
@Data
public class BaseDomain implements Serializable {

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Byte isDelete;
}
