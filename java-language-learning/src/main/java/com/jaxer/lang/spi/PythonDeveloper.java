package com.jaxer.lang.spi;

/**
 * Created by jaxer on 2020-04-04
 */
public class PythonDeveloper implements Developer {
	@Override
	public void sayHi() {
		System.out.println("Hi, I use Python.");
	}
}
