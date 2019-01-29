package com.jaxer.source;

import com.jaxer.doc.bean.Student;
import com.jaxer.doc.bean.StudentFactoryBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 FactoryBean
 * <p>
 * Created by jaxer on 2019/1/26
 */
public class FactoryBeanTests {

	@Test
	public void test() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("factoryBean.xml");
		StudentFactoryBean factoryBean = context.getBean("&studentFactoryBean", StudentFactoryBean.class);
		factoryBean.setType("student");
		Student student = factoryBean.getObject();
		System.out.println(student);

		Student student1 = context.getBean("student", Student.class);
		System.out.println(student1);
	}
}
