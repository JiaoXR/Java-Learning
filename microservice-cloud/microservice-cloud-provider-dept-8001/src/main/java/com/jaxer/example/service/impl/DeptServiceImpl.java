package com.jaxer.example.service.impl;

import com.jaxer.example.dao.DeptDAO;
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
    private DeptDAO deptDAO;

    public List<Dept> findAll() {
        return deptDAO.findAll();
    }
}
