package com.jaxer.example;

import java.util.concurrent.*;

/**
 * 代码千万行，注释第一行。
 * <p>
 * <p>
 * Created by jaxer on 2019-07-04
 */
public class CyclicBarrierTest {
	private static final int COUNT = 3;

	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT, () -> {
			System.out.println(Thread.currentThread().getName() + " start writing..");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("---------");
		});

		while (true) {
			for (int i = 0; i < COUNT; i++) {
				new Thread(() -> {
					System.out.println(Thread.currentThread().getName() + " is reading..");
					try {
						TimeUnit.SECONDS.sleep(3);
						cyclicBarrier.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}).start();
			}
			TimeUnit.SECONDS.sleep(10);
		}
	}
}
