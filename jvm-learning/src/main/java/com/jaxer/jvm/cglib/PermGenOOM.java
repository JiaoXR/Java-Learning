package com.jaxer.jvm.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created on 09:34 2020-01-13
 * <p>
 * 1.8
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
 * at java.lang.Class.forName0(Native Method)
 * at java.lang.Class.forName(Class.java:348)
 * <p>
 * 1.7
 * -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails
 * Exception in thread "main"
 * Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "main"
 *
 * @author jiaoxiangru
 */
public class PermGenOOM {
	public static void main(String[] args) {
		try {
			while (true) {
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(OOMObject.class);
				enhancer.setUseCache(false);
				enhancer.setCallback(new MethodInterceptor() {
					public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
						return methodProxy.invoke(o, objects);
					}
				});
				enhancer.create();
			}
		}
//		catch (InvocationTargetException e) {
//			System.out.println("此处接收被调用方法内部未被捕获的异常");
//			Throwable throwable = e.getTargetException();
//			throwable.printStackTrace();
//		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
