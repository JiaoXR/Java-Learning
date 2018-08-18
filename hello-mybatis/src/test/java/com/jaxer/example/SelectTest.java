package com.jaxer.example;

import com.jaxer.example.dao.EmployeeMapper;
import com.jaxer.example.domain.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 一些查询操作
 *
 * @author jaxer
 * date 18/08/2018
 */
public class SelectTest {
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
//        Map<String, Object> stringObjectMap = mapper.getEmpMapById(5);
        // 输出结果：{update_time=2018-02-02 15:12:18.0, gender=true, create_time=2018-02-02 15:11:26.0, name=Jerry, id=5, dept_id=3, age=24, status=1}
//        System.out.println(stringObjectMap);

        // id为key，Employee对象为value
//        Map<Integer, Employee> integerEmployeeMap = mapper.getEmpMapByNameLike("%a%");
//        System.out.println(integerEmployeeMap);

        // 封装复杂的结果集
        Employee empAndDept = mapper.getEmpAndDept(2);
        System.out.println(empAndDept);
    }

    private SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 开启自动提交
        return sqlSessionFactory.openSession(true);
    }
}
