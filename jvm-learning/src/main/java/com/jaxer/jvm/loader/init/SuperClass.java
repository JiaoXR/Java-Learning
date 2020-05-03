package com.jaxer.jvm.loader.init;

/**
 * Created by jaxer on 2020-03-03
 */
public class SuperClass {
	static {
		System.out.println("SuperClass init!");
	}

	public static int value = 123;
	public static final String HELLO_WORLD = "hello, world";
}
