package com.jaxer.example.controller;

import com.jaxer.example.bean.Department;
import com.jaxer.example.common.Result;
import com.jaxer.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门
 * <p>
 * Created by jaxer on 09/09/2018
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/depts")
    @ResponseBody
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    @RequestMapping(value = "/dept", method = RequestMethod.POST)
    @ResponseBody
    public Result insertDept(Department department) {
        System.out.println(department);
        if (department == null) {
            return Result.fail();
        }
//        departmentService.insertDept(department);
        return Result.success();
    }
}
