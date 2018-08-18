package com.jaxer.example.dao;

import com.jaxer.example.domain.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * 注解形式的 Mapper 接口
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from employee where id = #{id}")
    Employee getById(Integer id);
}
