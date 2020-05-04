package com.jaxer.jvm.memory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接内存溢出测试
 * VM Args: -Xmx20m -XX:MaxDirectMemorySize=10M
 * Created by jaxer on 2020-01-09
 */
public class DirectMemoryOOM {
	private static final int _1M = 2014 * 1024;

	public static void main(String[] args) {
		test2();
	}

	/**
	 * java.lang.OutOfMemoryError: Direct buffer memory
	 */
	private static void test2() {
		List<ByteBuffer> list = new ArrayList<>();
		while (true) {
//			ByteBuffer buffer = ByteBuffer.allocateDirect(_1M); // java.lang.OutOfMemoryError: Direct buffer memory
			ByteBuffer buffer = ByteBuffer.allocate(_1M); // java.lang.OutOfMemoryError: Java heap space
			list.add(buffer);
		}
	}

	/**
	 * 无OOM
	 */
	private static void test1() throws Exception {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while (true) {
			unsafe.allocateMemory(_1M);
		}
	}
}
