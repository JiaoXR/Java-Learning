package com.jaxer.example.controller;

import com.jaxer.example.domain.Dept;
import com.jaxer.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jaxer on 2018/10/31
 */
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> findAll() {
        return deptService.findAll();
    }
}
