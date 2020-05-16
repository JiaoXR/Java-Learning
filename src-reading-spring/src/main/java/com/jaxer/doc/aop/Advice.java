package com.jaxer.doc.aop;

/**
 * 测试AOP
 * <p>
 * Created by jaxer on 2019-12-16
 */
public class Advice {
	public void before() {
		System.out.println("before is called.");
	}

	public void after() {
		System.out.println("after is called.");
	}

	public void afterReturning() {
		System.out.println("afterReturning is called.");
	}
}
