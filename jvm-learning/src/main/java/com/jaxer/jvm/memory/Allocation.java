package com.jaxer.jvm.memory;

/**
 * 测试内存分配
 * <p>
 * Created by jaxer on 2020-02-27
 */
public class Allocation {
	private static final int _1M = 1024 * 1024;

	public static void main(String[] args) throws InterruptedException {
		System.gc();
//		testAllocation();
//		testPretenureSizeThreshold();
//		testTenuringThreshold();
		testTenuringThreshold2();
//		testHandlePromotion();
	}

	/**
	 * VM参数：-XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:-HandlePromotionFailure
	 */
	private static void testHandlePromotion() {
		byte[] a1, a2, a3, a4, a5, a6, a7;
		a1 = new byte[2 * _1M];
		a2 = new byte[2 * _1M];
		a3 = new byte[2 * _1M];
		a1 = null;
		a4 = new byte[2 * _1M];
		a5 = new byte[2 * _1M];
		a6 = new byte[2 * _1M];
		a4 = null;
		a5 = null;
		a6 = null;
		a7 = new byte[2 * _1M];
	}

	/**
	 * VM参数：-XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=15
	 * -XX:+PrintTenuringDistribution
	 */
	private static void testTenuringThreshold2() {
		byte[] a1, a2, a3, a4;
		a1 = new byte[_1M / 4];
		a2 = new byte[_1M / 4];

		a3 = new byte[4 * _1M];
		a4 = new byte[4 * _1M];
		a4 = null;
		a4 = new byte[4 * _1M];
	}

	/**
	 * 长期存活的对象直接进入老年代
	 * VM参数：-XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=1
	 * -XX:+PrintTenuringDistribution
	 */
	private static void testTenuringThreshold() {
		byte[] a1, a2, a3;
		a1 = new byte[_1M / 4];

		a2 = new byte[4 * _1M];
		a3 = new byte[4 * _1M];
		a3 = null;
		a3 = new byte[4 * _1M];
	}

	/**
	 * 大对象直接进入老年代
	 * VM参数：-XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:PretenureSizeThreshold=3145728
	 */
	private static void testPretenureSizeThreshold() {
		byte[] a;
		a = new byte[4 * _1M]; // 直接分配在老年代
	}

	/**
	 * 对象优先在 Eden 分配
	 * VM参数：-XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 */
	private static void testAllocation() {
		byte[] a1, a2, a3, a4;
		a1 = new byte[2 * _1M];
		a2 = new byte[2 * _1M];
		a3 = new byte[2 * _1M];
		a4 = new byte[4 * _1M]; // 出现一次 Minor GC
	}
}
