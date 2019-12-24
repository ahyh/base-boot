package com.yh.java8.pojo;

public class MapKey {

    private Integer age;

    public MapKey(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MapKey) {
            MapKey key = (MapKey) obj;
            return this.age.equals(key.getAge());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return age % 3;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
