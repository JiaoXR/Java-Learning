package com.jaxer.example;

import com.jaxer.example.service.GreetingService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试消费者
 * <p>
 * Created by jaxer on 2018/12/8
 */
public class ConsumerTests {
	@Test
	public void testConsumer() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.start();
		GreetingService greetingService = context.getBean("greetingService", GreetingService.class);
		String jack = greetingService.sayHello("Jack");
		System.out.println("consumer-->" + jack);
	}
}
