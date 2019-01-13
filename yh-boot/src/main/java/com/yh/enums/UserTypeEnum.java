package com.yh.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户类型枚举
 *
 * @author yanhuan
 */
public enum UserTypeEnum {

    /**
     * 普通用户
     */
    NORMAL(0, "普通用户"),

    /**
     * VIP用户
     */
    VIP(1, "VIP用户");

    public static final Map<Integer, UserTypeEnum> userTypeEnumMap = new HashMap<Integer, UserTypeEnum>();

    static {

        for (UserTypeEnum e : EnumSet.allOf(UserTypeEnum.class)) {
            userTypeEnumMap.put(e.getType(), e);
        }

    }

    public static UserTypeEnum getByType(Integer type) {
        return userTypeEnumMap.get(type);
    }

    private UserTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
