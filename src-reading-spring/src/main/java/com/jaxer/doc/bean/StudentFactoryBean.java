package com.jaxer.doc.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * FactoryBean 测试
 * <p>
 * Created by jaxer on 2019/1/26
 */
public class StudentFactoryBean implements FactoryBean<Student> {
	private String type;

	@Nullable
	@Override
	public Student getObject() throws Exception {
		if ("student".equals(type)) {
			return new Student();
		}
		return null;
	}

	@Nullable
	@Override
	public Class<?> getObjectType() {
		return Student.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
