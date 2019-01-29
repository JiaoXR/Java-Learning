package com.jaxer.source;

import com.jaxer.doc.method.lookup.Teacher;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 lookup-method & replace-method
 * <p>
 * Created by jaxer on 2019/1/29
 */
public class LookupMethodTests {

	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("method.xml");

		Teacher teacher = context.getBean("teacher", Teacher.class);
		System.out.println(teacher.show());

//		LookupMethod lookupMethod = context.getBean("lookupMethod", LookupMethod.class);
//		System.out.println(lookupMethod.show());
	}
}
