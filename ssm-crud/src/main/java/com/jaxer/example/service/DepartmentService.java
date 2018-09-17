package com.jaxer.example.service;

import com.jaxer.example.bean.Department;
import com.jaxer.example.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jaxer on 09/09/2018
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getAll() {
        return departmentMapper.getAll();
    }

    public void insertDept(Department department) {
        departmentMapper.insert(department);
    }
}
