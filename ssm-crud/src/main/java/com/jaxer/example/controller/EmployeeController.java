package com.jaxer.example.controller;

import com.jaxer.example.bean.Employee;
import com.jaxer.example.common.Result;
import com.jaxer.example.criteria.PagedCriteria;
import com.jaxer.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Controller
 * <p>
 * Created by jaxer on 06/09/2018
 */
@Controller
//@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/emps")
    @ResponseBody
    public Result getAll(PagedCriteria pagedCriteria) {
        List<Employee> employeeList = employeeService.getAll(pagedCriteria);
        Integer total = employeeService.countAll();
        employeeList.forEach(System.out::println);
        pagedCriteria.setData(employeeList);
        pagedCriteria.setTotal(total);
        return Result.success().add("employeeList", pagedCriteria);
//        return employeeList;
    }

//    @RequestMapping("/emps")
//    public String getAll(PagedCriteria pagedCriteria, Model model) {
//        List<Employee> employeeList = employeeService.getAll(pagedCriteria);
//        Integer total = employeeService.countAll();
//        employeeList.forEach(System.out::println);
//        pagedCriteria.setData(employeeList);
//        pagedCriteria.setTotal(total);
//        model.addAttribute("employeeList", pagedCriteria);
//        return "list";
//    }
}
