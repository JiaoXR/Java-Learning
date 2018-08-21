package com.jaxer.example.dao;

import com.jaxer.example.domain.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee getById(Integer id);

    Employee getByIdAndName(@Param("id") Integer id, String name);

    Employee getByMap(Map<String, Object> map);

    List<Employee> getByNameLike(String name);

    Map<String, Object> getEmpMapById(Integer id);

    // 指定返回 Map 的 key
    @MapKey("id")
    Map<Integer, Employee> getEmpMapByNameLike(String name);

    Employee getEmpAndDept(Integer id);

    List<Employee> getEmpListByDeptId(Integer deptId);

    void insertEmp(Employee employee);

    Long updateEmp(Employee employee);

    Integer deleteEmp(Integer id);
}
