package com.jaxer.jvm.memory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池OOM
 * VM Args:
 * 1.7: -XX:PermSize=8M -XX:MaxPermSize=8M
 * 1.8: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * Created by jaxer on 2020-01-09
 */
public class RuntimeConstantPoolOOM {
	static String baseStr = "string";

	public static void main(String[] args) {
		test1();
//		Set<String> set = new HashSet<>();
//		short i = 0;
//		while (true) {
//			set.add(String.valueOf(i++).intern());
//		}
//		heapOOM();
	}

	/**
	 * java.lang.OutOfMemoryError: Java heap space
	 */
	private static void heapOOM() {
		List<String> list = new ArrayList<>();
		while (true) {
			String s = baseStr + baseStr;
			baseStr = s;
			list.add(s.intern());
		}
	}

	private static void test1() {
		URL url;
		List<ClassLoader> list = new ArrayList<>();
		try {
			url = new File("/tmp").toURI().toURL();
			URL[] urls = {url};
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				System.out.println(i);
				URLClassLoader classLoader = new URLClassLoader(urls);
				list.add(classLoader);
				classLoader.loadClass("com.jaxer.com.jaxer.jvm.example.TestMeta");
			}
//			while (true) {
//				URLClassLoader classLoader = new URLClassLoader(urls);
//				list.add(classLoader);
//				classLoader.loadClass("com.jaxer.com.jaxer.jvm.example.TestMeta");
//			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
