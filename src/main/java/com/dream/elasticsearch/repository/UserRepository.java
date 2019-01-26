package com.dream.elasticsearch.repository;

import com.dream.elasticsearch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: wubo
 * @Date: 2019-01-26
 * @Time: 9:17
 * @Email: 343618924@qq.com
 * @Desc:
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {

}
