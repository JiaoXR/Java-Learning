package com.jaxer.source;

import com.jaxer.doc.aop.AopConfig;
import com.jaxer.doc.aop.UserDAO;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created on 2020/5/16 15:12
 * 测试AOP
 *
 * @author jaxer
 */
public class AopTests {
    @Test
    public void test02() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
        userDAO.getById();
    }

    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-aop.xml");
        UserDAO userDAO = context.getBean(UserDAO.class);
        userDAO.getById();
    }
}
