package com.jaxer.example.dao;

import com.jaxer.example.domain.Employee;

/**
 * 缓存机制
 * <p>
 * Created by jaxer on 27/08/2018
 */
public interface EmployeeCacheMapper {
    Employee getEmpById(Integer id);
}
