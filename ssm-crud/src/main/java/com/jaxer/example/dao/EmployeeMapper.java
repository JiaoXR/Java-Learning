package com.jaxer.example.dao;

import com.jaxer.example.bean.Employee;

/**
 * @author jaxer
 * date 26/07/2018
 */
public interface EmployeeMapper {
    public Employee getById(Integer id);
}
