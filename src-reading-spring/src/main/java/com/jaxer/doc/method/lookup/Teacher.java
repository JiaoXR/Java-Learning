package com.jaxer.doc.method.lookup;

/**
 * Created by jaxer on 2019/1/29
 */
public class Teacher extends User {
	@Override
	public String show() {
		System.out.println("Hello, I am a teacher");
		return "teacher";
	}
}
