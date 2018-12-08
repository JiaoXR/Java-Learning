package com.jaxer.doc.domain;

/**
 * 测试Bean
 * <p>
 * Created by jaxer on 2018/11/27
 */
public class Employee {
	private Integer id;
	private String name;
	private Integer age;

	public Employee() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
