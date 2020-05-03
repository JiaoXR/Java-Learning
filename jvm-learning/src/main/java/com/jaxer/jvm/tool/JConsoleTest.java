package com.jaxer.jvm.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jaxer on 2020-05-03
 * 测试JConsole
 */
public class JConsoleTest {
	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
	}

	private static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			TimeUnit.MILLISECONDS.sleep(100);
			list.add(new OOMObject());
		}
		System.gc();
	}

	private static class OOMObject {
		private byte[] placeholder = new byte[64 * 1024];
	}
}
