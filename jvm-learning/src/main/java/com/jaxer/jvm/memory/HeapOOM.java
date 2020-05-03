package com.jaxer.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xmn20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		while (true) {
			list.add(new OOMObject());
		}
	}

	static class OOMObject {

	}
}
