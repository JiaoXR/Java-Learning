package com.jaxer.source;

import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * 测试BeanFactory
 * <p>
 * Created by jaxer on 2018/11/27
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class BeanFactoryTests {

	@Test
	public void test2() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		ClassPathResource resource = new ClassPathResource("beans.xml");
		reader.loadBeanDefinitions(resource);
		System.out.println(beanFactory.getBean("employee"));
	}

	@Test
	public void test1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println(context.getBean("employee"));
	}

	@Test
	public void testBean() {
		ClassPathResource resource = new ClassPathResource("beans.xml");
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
		System.out.println(xmlBeanFactory.getBean("employee"));
	}
}
