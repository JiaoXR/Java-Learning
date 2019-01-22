package com.jaxer.doc.di;

import java.util.List;
import java.util.Set;

/**
 * list等集合类依赖注入
 *
 * @link https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/core.html#beans-collection-elements
 * <p>
 * Created by jaxer on 2018/12/3
 */
public class ComplexObject {
	private List<Object> list;
	private Set<String> set;

	public ComplexObject(List<Object> list) {
		this.list = list;
		System.out.println(list);
	}

	public ComplexObject(Set<String> set) {
		this.set = set;
		System.out.println(set);
	}

	@Override
	public String toString() {
		return "list: " + list + ", set: " + set;
	}
}
