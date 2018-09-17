package com.jaxer.example.service;

import com.jaxer.example.bean.Employee;
import com.jaxer.example.criteria.PagedCriteria;
import com.jaxer.example.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service
 * <p>
 * Created by jaxer on 06/09/2018
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getAll(PagedCriteria pagedCriteria) {
        return employeeMapper.getAll(pagedCriteria);
    }

    public Integer countAll() {
        return employeeMapper.countAll();
    }

    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }
}
