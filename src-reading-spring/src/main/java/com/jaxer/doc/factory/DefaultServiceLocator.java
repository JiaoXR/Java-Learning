package com.jaxer.doc.factory;

import com.jaxer.doc.service.ClientService2;
import com.jaxer.doc.service.impl.ClientServiceImpl;

/**
 * 实例工厂方法(Instance Factory Method)实例化Bean
 * <p>
 * Created by jaxer on 2018/12/1
 */
public class DefaultServiceLocator {
	private static ClientService2 clientService2 = new ClientServiceImpl();

	public ClientService2 getClientService2() {
		return clientService2;
	}

	public ClientService2 createClientService2() {
		return clientService2;
	}
}
