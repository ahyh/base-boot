package com.yh.spring.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SalaryQuery implements Serializable {

    private Long id;

    private Integer isDelete;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String name;

    private Integer age;

    private Byte sex;

    private String company;

    private BigDecimal salary;
}
