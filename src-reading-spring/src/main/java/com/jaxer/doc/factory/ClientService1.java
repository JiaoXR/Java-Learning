package com.jaxer.doc.factory;

/**
 * 静态工厂方法实例化Bean
 * <p>
 * Created by jaxer on 2018/12/1
 */
public class ClientService1 {
	private static ClientService1 clientService1 = new ClientService1();

	private ClientService1() {
	}

	public static ClientService1 getClientService1() {
		return clientService1;
	}
}
