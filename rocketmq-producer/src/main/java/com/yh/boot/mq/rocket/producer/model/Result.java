package com.yh.boot.mq.rocket.producer.model;

import java.io.Serializable;

/**
 * 封装返回结果的数据
 *
 * @param <T>
 * @author yanhuan
 */
public class Result<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
