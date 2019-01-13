package com.yh.pojo.condition;

import com.yh.pojo.BaseDomain;

/**
 * User查询条件
 *
 * @author yanhuan
 */
public class UserCondition extends BaseDomain {

    private String username;

    private String password;

    private Integer userType;

    private Integer userStatus;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
