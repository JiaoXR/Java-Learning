package com.jaxer.jvm.singleton;

/**
 * 使用内部嵌套类实现单例模式（利用JVM的类加载机制来保证线程安全）
 * Created by jaxer on 2020-03-05
 */
public class BeanFactory {
	private BeanFactory() {
	}

	public BeanFactory getBeanFactory() {
		return BeanFactoryHolder.beanFactory;
	}

	private static class BeanFactoryHolder {
		private static BeanFactory beanFactory = new BeanFactory();
	}
}
