package com.dream.elasticsearch.repository;

import com.dream.elasticsearch.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * @author: wubo
 * @Date: 2019-01-26
 * @Time: 9:17
 * @Email: 343618924@qq.com
 * @Desc:
 */
@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, Integer> {
}
