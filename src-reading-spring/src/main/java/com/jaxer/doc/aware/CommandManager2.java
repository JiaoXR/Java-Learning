package com.jaxer.doc.aware;

import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Method Injection
 * <p>
 * Created by jaxer on 2018/12/5
 */
public abstract class CommandManager2 {

	private ApplicationContext applicationContext;

	public Object process(Map commandState) {
		Command command = createCommand();
		command.setState(commandState);
		return command.execute();
	}

	protected abstract Command createCommand();

}
