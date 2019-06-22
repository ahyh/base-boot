package com.yh.boot.redis.cluster.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试Session共享的用户类
 *
 * @author yanhuan
 */
public class User implements Serializable {

    private String id;

    private String name;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
