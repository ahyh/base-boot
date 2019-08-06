package com.yh.boot.retrofit.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private BigDecimal salary;

    private Date birthday;

}
