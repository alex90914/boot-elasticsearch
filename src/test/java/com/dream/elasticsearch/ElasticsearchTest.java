package com.dream.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.dream.elasticsearch.model.User;
import com.dream.elasticsearch.repository.UserRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 不存在则新增,存在则修改
     */
    @Test
    public void saveOrUpdate() {
        for (long x = 0; x < 1000; x++) {
            User user = new User(x, "吴波" + x, "343618924@qq.com" + x, Math.random() * 1000 * x);
            userRepository.save(user);
        }
    }

    @Test
    public void getById() {
        Optional<User> optional = userRepository.findById(1L);
        User user = optional.get();
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void delete() {
        userRepository.delete(new User(1L));
    }

    @Test
    public void page() {
        QueryBuilder orderQuery = QueryBuilders.boolQuery();
        //设置排序方式
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "salary");
//        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = Sort.by(order1);
        Page<User> page = userRepository.search(orderQuery, PageRequest.of(0, 5, sort));
        List<User> users = page.getContent();
        long totalElements = page.getTotalElements();
        System.out.println(totalElements);
        System.out.println(JSON.toJSONString(page));


    }
}

