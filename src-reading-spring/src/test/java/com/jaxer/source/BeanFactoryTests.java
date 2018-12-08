package com.jaxer.source;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 测试BeanFactory
 * <p>
 * Created by jaxer on 2018/11/27
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class BeanFactoryTests {

	@Test
	public void testBean() {
		ClassPathResource resource = new ClassPathResource("beans.xml");
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
		System.out.println(xmlBeanFactory.getBean("employee"));
	}
}
