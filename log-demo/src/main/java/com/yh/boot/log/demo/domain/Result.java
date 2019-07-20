package com.yh.boot.log.demo.domain;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;
}
