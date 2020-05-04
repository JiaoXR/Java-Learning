package com.jaxer.jvm.arthas;

import java.util.concurrent.TimeUnit;

/**
 * Created by jaxer on 2020-05-04
 * 测试Arthas使用
 */
public class ArthasTest {
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			TimeUnit.SECONDS.sleep(5);
			new Hello().sayHello();
		}
	}
}
