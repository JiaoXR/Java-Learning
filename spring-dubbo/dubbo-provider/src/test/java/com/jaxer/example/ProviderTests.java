package com.jaxer.example;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 测试Provider
 * <p>
 * Created by jaxer on 2018/12/8
 */
public class ProviderTests {
	@Test
	public void testProvider() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.start();
		System.in.read();
	}
}
