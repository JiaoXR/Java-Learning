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

/**
 * 测试类
 * SqlSession 表示与数据库的一次会话，用完要关闭
 * SqlSession 和 connection 一样都是非线程安全的
 *
 * @author jaxer
 * date 16/08/2018
 */
public class MyBatisTest {
    /**
     * 方式一
     */
    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2. 获取SqlSession实例，能直接执行已经映射的SQL语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 唯一标识建议：命名空间+id【老版本的写法】
        Employee employee = sqlSession.selectOne("com.jaxer.example.dao.EmployeeMapper.getById", 1);
        System.out.println(employee);
        sqlSession.close();
    }

    /**
     * 方式二：接口式编程（常用）
     */
    @Test
    public void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取接口的实现类
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getById(1);
            //class com.sun.proxy.$Proxy5，生成的代理对象
            System.out.println(employeeMapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据XML配置文件，创建一个 SqlSessionFactory
     */
    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        // 1.根据XML配置文件，创建一个SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
