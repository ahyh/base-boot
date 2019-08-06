package com.yh.boot.retrofit.demo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQuery implements Serializable {

    private String name;

    private Integer age;
}
