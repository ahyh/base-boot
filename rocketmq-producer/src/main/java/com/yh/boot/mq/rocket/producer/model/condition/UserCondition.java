package com.yh.boot.mq.rocket.producer.model.condition;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User查询条件
 *
 * @author yanhuan
 */
public class UserCondition implements Serializable{

    private Long id;

    private String name;

    private Integer age;

    private BigDecimal salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
