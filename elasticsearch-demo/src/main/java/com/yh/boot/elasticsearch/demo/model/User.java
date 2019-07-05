package com.yh.boot.elasticsearch.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试ES的实例
 *
 * @author yanhuan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "cosmo", type = "user", shards = 3, replicas = 2, refreshInterval = "-1")
public class User implements Serializable {

    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Text)
    private String password;

    @Field(type = FieldType.Text)
    private String phone;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Integer)
    private Integer userType;

    @Field(type = FieldType.Integer)
    private Integer userStatus;

    @Field(type = FieldType.Integer)
    private Integer isDelete;

    @Field(type = FieldType.Text)
    private String createUser;

    @Field(type = FieldType.Text)
    private String createTime;

    @Field(type = FieldType.Text)
    private String updateUser;

    @Field(type = FieldType.Text)
    private String updateTime;

}
