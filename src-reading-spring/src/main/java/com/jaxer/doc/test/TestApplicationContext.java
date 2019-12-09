package com.jaxer.doc.test;

import com.jaxer.doc.bean.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 代码千万行，注释第一行。
 * <p>
 * <p>
 * Created by jaxer on 2019-10-29
 */
public class TestApplicationContext {
	public static void main(String[] args) {
//		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:application.xml");
		// 相对路径
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"/target/classes/application.xml");

//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml"); // ok
		PersonService personService = context.getBean("personService", PersonService.class);
		personService.printName("Jack");
	}
}
