package com.jaxer.jvm.loader.init;

/**
 * Created by jaxer on 2020-03-03
 */
public class NotInitialization {
	public static void main(String[] args) {
//		System.out.println(SubClass.value); // SuperClass 加载，初始化

//		SuperClass[] superClass = new SuperClass[10]; // SuperClass 加载
//		SuperClass[][] superClass1 = new SuperClass[10][]; // SuperClass 加载

//		System.out.println(SubClass.HELLO_WORLD); // SuperClass 未加载

		new SubClass();
	}
}
