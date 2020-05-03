package com.jaxer.jvm.egs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jaxer on 2020-05-02
 */
public class Example1 {
	private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(
			50, new ThreadPoolExecutor.DiscardOldestPolicy());

	public static void main(String[] args) throws InterruptedException {
		for (; ; ) {
			modelFit();
			TimeUnit.MILLISECONDS.sleep(100);
		}
	}

	private static void modelFit() {
		List<CardInfo> allCardInfo = getAllCardInfo();
		allCardInfo.forEach(
				cardInfo -> executorService.scheduleWithFixedDelay(cardInfo::m,
						2, 3, TimeUnit.SECONDS)
		);
	}

	private static List<CardInfo> getAllCardInfo() {
		List<CardInfo> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			list.add(new CardInfo());
		}
		return list;
	}

	private static class CardInfo {
		BigDecimal bigDecimal = new BigDecimal(0.0);
		String name = "Jack";
		int age = 22;
//		byte[] placeholder = new byte[1024 * 256];

		public void m() {
		}
	}
}
