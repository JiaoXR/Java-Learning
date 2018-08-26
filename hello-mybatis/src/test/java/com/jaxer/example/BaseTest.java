package com.jaxer.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试类基类
 * <p>
 * Created by jaxer on 26/08/2018
 */
public abstract class BaseTest {
    /**
     * 根据XML配置文件，创建一个 SqlSession
     */
    protected SqlSession getSqlSession() throws IOException {
        // 1.根据XML配置文件，创建一个SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 注意这里获取的 SqlSession 参数为 true 才自动提交（commit）
        return sqlSessionFactory.openSession(true);
    }
}
