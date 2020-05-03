package com.jaxer.jvm.memory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM Args:
 * 1.7: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 1.8: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * Created by jaxer on 2020-01-12
 */
public class MethodAreaOOM {
	public static void main(final String[] args) {
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
					return methodProxy.invokeSuper(o, objects);
				}
			});
			enhancer.create();
		}
	}

	static class OOMObject {
	}
}
