package com.dream.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author: wubo
 * @Date: 2019-01-26
 * @Time: 9:13
 * @Email: 343618924@qq.com
 * @Desc:
 */
@Document(indexName = "user", shards = 2, refreshInterval = "-1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String name;
    private String email;
    private Double salary;

    public User(Long id) {
        this.id = id;
    }
}
