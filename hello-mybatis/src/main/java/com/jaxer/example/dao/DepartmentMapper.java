package com.jaxer.example.dao;

import com.jaxer.example.domain.Department;

public interface DepartmentMapper {
    Department getById(Integer id);

    /**
     * 查询部门及员工信息
     *
     * @param id 部门id
     */
    Department getDeptAndEmpListById(Integer id);

    /**
     * 查询部门及员工信息【分步查询】
     *
     * @param id 部门id
     */
    Department getDeptAndEmpListByStep(Integer id);
}
