package com.jaxer.example;

import com.jaxer.example.dao.EmployeeDynamicMapper;
import com.jaxer.example.domain.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 测试动态SQL
 * <p>
 * Created by jaxer on 26/08/2018
 */
public class DynamicSQLTest extends BaseTest {
    @Test
    public void test1() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        Employee employee = new Employee(2, "Luna", 24, null, null);
        // where/if/choose 标签
        List<Employee> employeeList = mapper.listEmpBy(employee);
        employeeList.forEach(System.out::println);

        // set 标签
//        mapper.updateEmp(employee);

//        List<Employee> employeeList = mapper.getByIdList(Arrays.asList(1, 2, 3, 5));
//        employeeList.forEach(System.out::println);
    }

    @Test
    public void test2() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        List<Employee> employeeList = Arrays.asList(new Employee(null, "Zoffy", 25, 1, 4),
                new Employee(null, "Seven", 19, 1, 4));
        mapper.insertEmps(employeeList);
    }
}
