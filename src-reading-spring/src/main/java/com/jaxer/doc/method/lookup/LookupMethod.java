package com.jaxer.doc.method.lookup;

/**
 * Created by jaxer on 2019/1/29
 * 测试 lookup-method
 */
public abstract class LookupMethod {

	public String show() {
		return getUser().show();
	}

	// 这里可以是抽象方法
//	abstract User getUser();

	public User getUser() {
		return null;
	}
}
