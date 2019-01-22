package com.jaxer.source;

import com.jaxer.doc.service.EmployeeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * ApplicationContext测试
 * <p>
 * Created by jaxer on 2018/11/28
 */
public class ApplicationContextTests {
	@Test
	public void testApplicationContext() {
		//相对路径
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans.xml");
		//绝对路径
//		ApplicationContext context = new FileSystemXmlApplicationContext("//Users/jaxer/GitHub-JiaoXR/Framework/src-reading-spring/src/main/resources/beans.xml");

//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/beans.xml");
		EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);
		employeeService.printName();

		System.out.println(context.getBean("name1"));
		System.out.println(context.getBean("name2"));
		System.out.println(context.getBean("emp") == context.getBean("name2")); //true
	}
}