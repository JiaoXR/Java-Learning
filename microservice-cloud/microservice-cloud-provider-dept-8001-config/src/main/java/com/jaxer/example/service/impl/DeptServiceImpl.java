package com.jaxer.example.service.impl;

import com.jaxer.example.dao.DeptDAO1;
import com.jaxer.example.domain.Dept;
import com.jaxer.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jaxer on 2018/10/31
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDAO1 deptDAO1;

    @Override
    public boolean insert(Dept dept) {
        return deptDAO1.insert(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptDAO1.findById(id);
    }

    public List<Dept> findAll() {
        return deptDAO1.findAll();
    }
}
