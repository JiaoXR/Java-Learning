package com.jaxer.example;

import com.jaxer.example.dao.EmployeeCacheMapper;
import com.jaxer.example.domain.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 缓存机制
 * <p>
 * Created by jaxer on 27/08/2018
 */
public class CacheTest {
    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeCacheMapper mapper = sqlSession.getMapper(EmployeeCacheMapper.class);
        Employee employee = mapper.getEmpById(2);
        System.out.println(employee);
        sqlSession.close();

        // 这里用到了二级缓存，未发送SQL查询
        // DEBUG [main] - Cache Hit Ratio [com.jaxer.example.dao.EmployeeCacheMapper]: 0.5
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        EmployeeCacheMapper mapper2 = sqlSession2.getMapper(EmployeeCacheMapper.class);
        Employee employee2 = mapper2.getEmpById(2);
        System.out.println(employee2);
        sqlSession2.close();
    }
}
