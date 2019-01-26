package com.dream.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author wubo
 * @Date 2019-01-26
 * @Time 15 00
 * @Email 343618924@qq.com
 * @Desc Oracle员工测试
 */
@Data
@Document(indexName = "bank", type = "account", refreshInterval = "-1")
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    @Id
    private Integer account_number;
    private Integer balance;
    private String firstname;
    private String lastname;
    private Integer age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}
