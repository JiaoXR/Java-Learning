package com.jaxer.jvm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * VM Args: -Xmn20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
	public static void main(String[] args) throws InterruptedException {
		List<Object> list = new ArrayList<>();
		while (true) {
			list.add(new OOMObject());
			TimeUnit.MILLISECONDS.sleep(10);
		}
	}

	static class OOMObject {
		byte[] bytes = new byte[1024 * 100];
	}
}
