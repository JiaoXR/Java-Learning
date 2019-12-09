package com.jaxer.source;

import com.jaxer.doc.bean.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 代码千万行，注释第一行。
 * Spring整合junit
 * <p>
 * Created by jaxer on 2019-11-03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class SpringTest {
	@Autowired
	private PersonService personService;

	@Test
	public void print() {
		personService.printName("Tom");
	}
}
