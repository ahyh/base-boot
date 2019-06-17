package com.yh.dto;

import java.io.Serializable;

/**
 * 返回结果包装对象
 *
 * @param <T>
 * @author yanhuan
 */
public class Result<T> implements Serializable {

    private Integer code;

    private T data;

    private String message;

    public Result() {
    }

    public Result(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
