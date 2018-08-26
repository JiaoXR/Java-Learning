package com.jaxer.example;

import com.jaxer.example.dao.DepartmentMapper;
import com.jaxer.example.dao.EmployeeMapper;
import com.jaxer.example.domain.Department;
import com.jaxer.example.domain.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 一些查询操作
 *
 * @author jaxer
 * date 18/08/2018
 */
public class SelectTest extends BaseTest {
    @Test
    public void getList() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employeeList = mapper.getByNameLike("%a%");
        employeeList.forEach(System.out::println);
    }

    @Test
    public void testMap() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Map<String, Object> stringObjectMap = mapper.getEmpMapById(5);
        // 输出结果：{update_time=2018-02-02 15:12:18.0, gender=true, create_time=2018-02-02 15:11:26.0, name=Jerry, id=5, dept_id=3, age=24, status=1}
        System.out.println(stringObjectMap);

        // id为key，Employee对象为value
        Map<Integer, Employee> integerEmployeeMap = mapper.getEmpMapByNameLike("%a%");
        System.out.println(integerEmployeeMap);
    }

    @Test
    public void testAssociation() throws IOException {
        SqlSession sqlSession = getSqlSession();
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // 封装复杂的结果集
//        Employee empAndDept = mapper.getEmpAndDept(2);
//        System.out.println(empAndDept);

        // 查询部门及员工信息
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department deptAndEmpList = departmentMapper.getDeptAndEmpListById(3);
        System.out.println(deptAndEmpList);

        // 查询部门及员工信息【分步查询】
//        Department listByStep = departmentMapper.getDeptAndEmpListByStep(3);
//        System.out.println(listByStep);
    }

    @Test
    public void testDepartment() throws IOException {
        SqlSession sqlSession = getSqlSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = mapper.getById(1);
        System.out.println(department);
    }
}
