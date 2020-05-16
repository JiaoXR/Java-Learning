package com.jaxer.source;

import com.jaxer.doc.config.BeanConfig;
import com.jaxer.doc.ioc.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContext测试
 * <p>
 * Created by jaxer on 2018/11/28
 */
public class ApplicationContextTests {
    @Test
    public void test03() {
        System.out.println(ApplicationContextTests.this);
        System.out.println(this);
        System.out.println(new ApplicationContextTests());
        System.out.println(ApplicationContextTests.class);
    }

    /**
     * 注解配置
     */
    @Test
    public void test02() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }

    /**
     * XML配置
     */
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-ioc.xml");
        System.out.println(context.getBean("person"));
        System.out.println(context.getBean("person"));
    }
}