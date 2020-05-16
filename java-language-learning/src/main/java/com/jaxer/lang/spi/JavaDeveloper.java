package com.jaxer.lang.spi;

/**
 * Created by jaxer on 2020-04-04
 */
public class JavaDeveloper implements Developer {
	@Override
	public void sayHi() {
		System.out.println("Hi, I use Java.");
	}
}
