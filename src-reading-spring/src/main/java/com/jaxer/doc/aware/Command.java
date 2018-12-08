package com.jaxer.doc.aware;

import java.util.Map;

/**
 * Created by jaxer on 2018/12/5
 */
public class Command {
	private Map state;

	public Object execute() {
		System.out.println("---execute---");
		return null;
	}

	public Map getState() {
		return state;
	}

	public void setState(Map state) {
		this.state = state;
	}
}
