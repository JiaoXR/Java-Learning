package com.jaxer.source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类基类
 * <p>
 * Created by jaxer on 2018/12/5
 */
public abstract class BaseTests {

	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

}
