package com.jaxer.example.service;

import com.jaxer.example.domain.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Feign: 接口+注解实现负载均衡
 * <p>
 * Created by jaxer on 2018/11/8
 */
//@FeignClient(value = "MICROSERVICE-CLOUD-DEPT")
@FeignClient(value = "MICROSERVICE-CLOUD-DEPT", fallback = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    boolean insert(Dept dept);

    @RequestMapping(value = "/dept/{id}", method = RequestMethod.GET)
    Dept findById(Integer id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    List<Dept> findAll();
}
