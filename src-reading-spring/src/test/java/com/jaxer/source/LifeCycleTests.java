package com.jaxer.source;

import com.jaxer.doc.bean.LifeCycle;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Bean生命周期
 * <p>
 * Created by jaxer on 2018/12/23
 */
public class LifeCycleTests {

	@Test
	public void test() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("lifeCycle.xml");
		LifeCycle hello = ctx.getBean("lifeCycle", LifeCycle.class);
		hello.hello();
		ctx.close();
	}
}
