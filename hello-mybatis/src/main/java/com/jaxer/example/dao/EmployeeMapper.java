package com.jaxer.example.dao;

import com.jaxer.example.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EmployeeMapper {
    Employee getById(Integer id);

    Employee getByIdAndName(@Param("id") Integer id, String name);

    Employee getByMap(Map<String, Object> map);

    void insertEmp(Employee employee);

    Long updateEmp(Employee employee);

    Integer deleteEmp(Integer id);
}
