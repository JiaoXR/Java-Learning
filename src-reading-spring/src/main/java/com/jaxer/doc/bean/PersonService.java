package com.jaxer.doc.bean;

/**
 * 代码千万行，注释第一行。
 * 测试IoC
 * <p>
 * Created by jaxer on 2019-10-31
 */
public class PersonService {
	private String name;
	private EmployeeService employeeService;

	public void initMethod() {
		System.out.println("initMethod is called.");
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void printName(String name) {
		System.out.println("hello, " + name);
		System.out.println("I'm " + this.name);
		employeeService.print();
	}
}
