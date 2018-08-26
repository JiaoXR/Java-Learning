package com.jaxer.example.dao;

import com.jaxer.example.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态SQL
 * <p>
 * Created by jaxer on 26/08/2018
 */
public interface EmployeeDynamicMapper {
    List<Employee> listEmpBy(Employee employee);

    void updateEmp(Employee employee);

    List<Employee> getByIdList(List<Integer> idList);

    /**
     * 批量插入
     */
    void insertEmps(@Param("empList") List<Employee> employeeList);
}
