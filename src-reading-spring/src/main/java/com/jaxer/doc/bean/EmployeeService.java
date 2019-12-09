package com.jaxer.doc.bean;

/**
 * 代码千万行，注释第一行。
 * <p>
 * <p>
 * Created by jaxer on 2019-12-08
 */
public class EmployeeService {
	private PersonService personService;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void print() {
		System.out.println("personService");
	}
}
