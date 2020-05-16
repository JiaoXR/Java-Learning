package com.jaxer.lang.spi;

import java.util.ServiceLoader;

/**
 * Created by jaxer on 2020-04-04
 * 测试SPI
 */
public class TestSPI {
	public static void main(String[] args) {
		ServiceLoader<Developer> loader = ServiceLoader.load(Developer.class);
		loader.forEach(Developer::sayHi);
	}
}
