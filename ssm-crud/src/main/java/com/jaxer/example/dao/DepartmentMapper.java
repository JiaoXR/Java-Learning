package com.jaxer.example.dao;

import com.jaxer.example.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    List<Department> getAll();

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}