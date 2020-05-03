package com.jaxer.jvm.loader.init;

/**
 * Created by jaxer on 2020-03-03
 */
public class SubClass extends SuperClass {
	static {
		System.out.println("SubClass init!");
	}
}
