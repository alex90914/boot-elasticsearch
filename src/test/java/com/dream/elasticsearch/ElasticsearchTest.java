package com.dream.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.dream.elasticsearch.model.Employee;
import com.dream.elasticsearch.model.User;
import com.dream.elasticsearch.repository.EmployeeRepository;
import com.dream.elasticsearch.repository.UserRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;

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
    public void page() {//sourceBuilder.fetchSource(includeFields, excludeFields);
        BoolQueryBuilder builder = QueryBuilders.boolQuery()
                .must(QueryBuilders.fuzzyQuery("firstname", "Dillard"));
//                .must(QueryBuilders.rangeQuery("age").lte(40).gte(35))
//                .must(QueryBuilders.fuzzyQuery("address", "Avenue"));
        Page<Employee> page = employeeRepository.search(builder, PageRequest.of(0, 10));
        System.out.println("总共员工数量" + page.getTotalElements());
        System.out.println(JSON.toJSONString(page));
    }

    @Test
    public void filter() {
        BoolQueryBuilder builder = QueryBuilders.boolQuery()
                .must(QueryBuilders.rangeQuery("age").lte(40).gte(35))
                .must(QueryBuilders.fuzzyQuery("address", "Avenue"));
        String[] fields = new String[]{"account_number", "balance", "age"};
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withFields(fields)
                .build().setPageable(PageRequest.of(0, 100));
        AggregatedPage<Employee> employees = elasticsearchTemplate.queryForPage(searchQuery, Employee.class);
        employees.get().forEach(employee -> System.out.println(JSON.toJSONString(employee)));

        Class clazz = Employee.class;
        // 获取实体类的所有属性信息，返回Field数组
        Field[] javaFields = clazz.getDeclaredFields();
        for (Field field : javaFields) {
            System.out.println(field.getName());
        }


    }
}

