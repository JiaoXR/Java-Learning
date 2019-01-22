package com.jaxer.doc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Bean生命周期
 * <p>
 * Created by jaxer on 2018/12/23
 */
public class LifeCycle implements InitializingBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean {

	private String message;

	public LifeCycle() {
		System.out.println("LifeCycle # constructor");
	}

	public void setMessage(String message) {
		System.out.println("LifeCycle # setter");
		this.message = message;
	}

	@Override
	public void setBeanName(String s) {
		System.out.println("LifeCycle -> BeanNameAware#setBeanName " + s);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("LifeCycle -> BeanFactoryAware#setBeanFactory ");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("LifeCycle -> ApplicationContextAware#setApplicationContext ");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("LifeCycle @PostConstruct");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("LifeCycle -> InitializingBean#afterPropertiesSet");
	}

	public void init_method() {
		System.out.println("LifeCycle # init-method ");
	}

	public void hello() {
		System.out.println("hello! message=" + message);
	}

	@PreDestroy
	public void preDestroy() throws Exception {
		System.out.println("LifeCycle @PreDestroy ");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("LifeCycle -> DisposableBean#destroy ");
	}

	public void destroy_method() throws Exception {
		System.out.println("LifeCycle destroy-method ");
	}

}
