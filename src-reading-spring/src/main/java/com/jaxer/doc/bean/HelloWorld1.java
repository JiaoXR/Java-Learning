package com.jaxer.doc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * Created by jaxer on 2018/12/23
 */
public class HelloWorld1 implements BeanPostProcessor, Ordered {
	@Override
	public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
		System.out.println("BeanPostProcessor1.postProcessBeforeInitialization: " + s);
		return o;
	}

	@Override
	public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
		System.out.println("BeanPostProcessor1.postProcessAfterInitialization: " + s);
		return o;
	}

	@Override
	public int getOrder() {
		return 10;
	}
}
