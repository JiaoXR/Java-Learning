package com.jaxer.example;

import com.jaxer.example.dao.EmployeeMapper;
import com.jaxer.example.domain.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CRUD
 * sqlSessionFactory.openSession(): 不会自动提交，需要调用 sqlSession.commit() 手动提交
 * sqlSessionFactory.openSession(true): 自动提交；
 *
 * @author jaxer
 * date 18/08/2018
 */
public class CRUDTest extends BaseTest {
    @Test
    public void testInsert() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(null, "Bran Stark", 13, 1, 1);
        mapper.insertEmp(employee);
        System.out.println(employee.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 增删改方法可以有返回值，类型为 Integer、Long 或 Boolean
     * 前两个表示影响多少行，Boolean 表示是否影响到（只需在接口方法加返回值类型即可）
     */
    @Test
    public void testUpdate() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(30, "Hel", 21, 1, 1);
        Long aLong = mapper.updateEmp(employee);
        System.out.println(aLong);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据参数查询
     */
    @Test
    public void getByParams() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getByIdAndName(2, "Lucy");
        System.out.println(employee);

        // 根据 Map 查询
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "Tom");
        Employee employee1 = mapper.getByMap(map);
        System.out.println(employee1);

        sqlSession.close();
    }

    @Test
    public void testDelete() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Integer integer = mapper.deleteEmp(1);
        System.out.println(integer);
        sqlSession.commit();
        sqlSession.close();
    }
}
