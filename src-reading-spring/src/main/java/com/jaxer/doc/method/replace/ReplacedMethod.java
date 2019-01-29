package com.jaxer.doc.method.replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by jaxer on 2019/1/29
 * 测试 replaced-method
 */
public class ReplacedMethod implements MethodReplacer {

	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		if ("show".equals(method.getName())) {
			System.out.println("method '" + method.getName() + "()' has been replaced");
			return "cat";
		}
		return null;
	}
}
