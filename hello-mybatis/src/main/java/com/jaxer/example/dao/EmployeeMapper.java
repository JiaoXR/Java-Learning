package com.jaxer.example.dao;

import com.jaxer.example.domain.Employee;

public interface EmployeeMapper {
    Employee getById(Integer id);
}
