package com.jaxer.jvm.classloader;

/**
 * Created by jaxer on 2020-05-04
 */
public class TestMyClassLoader {
	public static void main(String[] args) throws Exception {
		test1();
	}

	private static void test1() throws Exception {
		// 创建类加载器实例
		MyClassLoader myClassLoader1 = new MyClassLoader();
		// 加载 Person 类（注意这里是 loadClass 方法）
		Class<?> aClass1 = myClassLoader1.loadClass("com.jaxer.jvm.classloader.Person");
		aClass1.newInstance(); // Person init!

		MyClassLoader myClassLoader2 = new MyClassLoader();
		Class<?> aClass2 = myClassLoader2.loadClass("com.jaxer.jvm.classloader.Person");
		aClass2.newInstance();

		System.out.println("--->" + aClass1.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
		System.out.println("--->" + aClass2.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
		System.out.println("--->" + aClass1.equals(aClass2)); // true
	}

	private static void test2() throws Exception {
		MyClassLoader cl1 = new MyClassLoader();
		// 加载自定义的 Person 类
		Class<?> aClass1 = cl1.findClass("com.jaxer.jvm.classloader.Person");
		// 实例化 Person 对象
		aClass1.newInstance(); // Person init!

		MyClassLoader cl2 = new MyClassLoader();
		Class<?> aClass2 = cl2.findClass("com.jaxer.jvm.classloader.Person");
		aClass2.newInstance(); // Person init!

		System.out.println("--->" + aClass1); // class loader.Person
		System.out.println("--->" + aClass2); // class loader.Person

		System.out.println("--->" + aClass1.getClassLoader()); // loader.MyClassLoader@60e53b93
		System.out.println("--->" + aClass2.getClassLoader()); // loader.MyClassLoader@1d44bcfa

		System.out.println("--->" + aClass1.equals(aClass2)); // false
	}

	private static void test3() throws Exception {
		MyClassLoader cl1 = new MyClassLoader();
		Class<?> aClass1 = cl1.findClass("com.jaxer.jvm.classloader.Person");
		aClass1.newInstance();

		// 这里改用上面的类加载进行加载呢？
		Class<?> aClass2 = cl1.findClass("com.jaxer.jvm.classloader.Person");
		aClass2.newInstance();

		System.out.println("--->" + aClass1);
		System.out.println("--->" + aClass2);
		System.out.println("--->" + aClass1.equals(aClass2)); // true ??
	}
}
