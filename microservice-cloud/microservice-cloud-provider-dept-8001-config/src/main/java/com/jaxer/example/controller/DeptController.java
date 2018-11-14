package com.jaxer.example.controller;

import com.jaxer.example.domain.Dept;
import com.jaxer.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jaxer on 2018/10/31
 * 测试动态切换配置
 */
@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/{id}", method = RequestMethod.GET)
    public Dept getById(@PathVariable("id") Integer id) {
        return deptService.findById(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> findAll() {
        return deptService.findAll();
    }
}
