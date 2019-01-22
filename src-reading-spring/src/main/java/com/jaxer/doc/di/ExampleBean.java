package com.jaxer.doc.di;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by jaxer on 2018/12/2
 */
public class ExampleBean {
	// Number of years to calculate the Ultimate Answer
	private int years;

	// The Answer to Life, the Universe, and Everything
	private String ultimateAnswer;

	@PostConstruct
	public void init() {
		System.out.println("init----");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("destroy----");
	}

	public ExampleBean() {
		System.out.println("ExampleBean 构造器");
	}

	public ExampleBean(int years, String ultimateAnswer) {
		this.years = years;
		this.ultimateAnswer = ultimateAnswer;
		System.out.println("ExampleBean 构造器2");
	}

	//可修改配置名称
//	@ConstructorProperties({"years1", "ultimateAnswer1"})
//	public ExampleBean(int years, String ultimateAnswer) {
//		this.years = years;
//		this.ultimateAnswer = ultimateAnswer;
//	}

	@Override
	public String toString() {
		return "years: " + years + ", ultimateAnswer: " + ultimateAnswer;
	}
}
