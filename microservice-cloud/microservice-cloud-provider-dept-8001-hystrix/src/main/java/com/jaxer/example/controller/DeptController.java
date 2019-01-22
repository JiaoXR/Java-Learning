package com.jaxer.example.controller;

import com.jaxer.example.domain.Dept;
import com.jaxer.example.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by jaxer on 2018/11/09
 */
@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptService.insert(dept);
    }

    @RequestMapping(value = "/dept/{id}", method = RequestMethod.GET)
    //若调用 @HystrixCommand 注解标注的方法失败抛出异常，则会调用 fallbackMethod 指定的方法进行兜底
    @HystrixCommand(fallbackMethod = "processHystrix")
    public Dept getById(@PathVariable("id") Integer id) {
        Dept dept = deptService.findById(id);
        if (dept == null) {
            throw new RuntimeException("该id没有对应信息, id-->" + id);
        }
        return dept;
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> findAll() {
        return deptService.findAll();
    }

    /**
     * 服务发现
     */
    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> services = client.getServices();
        log.info("services-->{}", services);
        return client.getInstances("MICROSERVICE-CLOUD-DEPT");
    }

    public Dept processHystrix(@PathVariable("id") Integer id) {
        return new Dept().setId(id).setName("未知-->@HystrixCommand").setDb(0).setCreateTime(new Date());
    }
}
