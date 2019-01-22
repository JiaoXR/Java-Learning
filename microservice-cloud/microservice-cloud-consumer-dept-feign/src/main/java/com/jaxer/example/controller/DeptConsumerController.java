package com.jaxer.example.controller;

import com.jaxer.example.domain.Dept;
import com.jaxer.example.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 消费者Controller
 * <p>
 * Created by jaxer on 2018/11/2
 */
@RestController
public class DeptConsumerController {
    @Autowired
    private DeptClientService clientService;

    @RequestMapping(value = "/consumer/dept/add", method = RequestMethod.POST)
    public boolean insert(Dept dept) {
        return clientService.insert(dept);
    }

    @RequestMapping(value = "/consumer/dept/{id}", method = RequestMethod.GET)
    public Dept findById(@PathVariable("id") Integer id) {
        // FIXME: 2018/11/11
        // java.net.URISyntaxException: Illegal character in path at index 36: http://MICROSERVICE-CLOUD-DEPT/dept/{id}
        return clientService.findById(id);
    }

    @RequestMapping(value = "/consumer/dept/list", method = RequestMethod.GET)
    public List<Dept> findAll() {
        return clientService.findAll();
    }
}
