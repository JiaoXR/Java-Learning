package com.jaxer.jvm.classloader;

/**
 * Created by jaxer on 2020-05-04
 */
public class Person {
	static {
		// 当 Person 类初始化时，会打印该代码
		System.out.println("Person init!");
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
