package com.jaxer.example.controller;

import com.jaxer.example.domain.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 消费者Controller
 * <p>
 * Created by jaxer on 2018/11/2
 */
@RestController
public class DeptConsumerController {
    private static final String PROVIDER_PREFIX = "http://localhost:8001/";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(PROVIDER_PREFIX + "dept/add", dept, Boolean.class);
    }

    @RequestMapping(value = "consumer/dept/get/{id}")
    public Dept findById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PROVIDER_PREFIX + "dept/" + id, Dept.class);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "consumer/dept/list")
    public List<Dept> findAll() {
        return restTemplate.getForObject(PROVIDER_PREFIX + "dept/list", List.class);
    }

    /**
     * 测试服务发现
     */
    @RequestMapping(value = "consumer/dept/discovery")
    public Object discovery() {
        return restTemplate.getForObject(PROVIDER_PREFIX + "dept/discovery", Object.class);
    }
}
