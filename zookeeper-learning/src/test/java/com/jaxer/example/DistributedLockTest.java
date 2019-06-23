package com.jaxer.example;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 代码千万行，注释第一行。
 * Created by jiaoxiangru on 14:15 2019-06-18
 * Curator 分布式锁举例
 */
public class DistributedLockTest {

	@Test
	public void orders() {
		// 模拟高并发场景下生成订单号（很可能重复）
		CountDownLatch countDownLatch = new CountDownLatch(1);
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					countDownLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("timestamp: " + System.currentTimeMillis() + ", thread: " + Thread.currentThread().getName());
			}).start();
		}
		countDownLatch.countDown();
        /*
            参考结果：
            timestamp: 1560839799927, thread: Thread-0
            timestamp: 1560839799927, thread: Thread-1
            timestamp: 1560839799927, thread: Thread-7
            timestamp: 1560839799927, thread: Thread-5
            timestamp: 1560839799927, thread: Thread-8
            timestamp: 1560839799927, thread: Thread-4
            timestamp: 1560839799927, thread: Thread-2
            timestamp: 1560839799927, thread: Thread-3
            timestamp: 1560839799927, thread: Thread-9
            timestamp: 1560839799927, thread: Thread-6
         */
	}

	private static CuratorFramework zkClient2;

	@Before
	public void connect() {
		// 连接 ZK 服务器（两种方式）
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

		zkClient2 = CuratorFrameworkFactory.builder().connectString(ZKConstant.CONNECT_STRING)
				.sessionTimeoutMs(ZKConstant.SESSION_TIMEOUT).retryPolicy(retryPolicy).namespace("base").build();
		zkClient2.start();
	}

	@Test
	public void distributedLock() throws InterruptedException {
		// 使用 Curator 的分布式锁生成订单（时间戳不重复）
		String lockPath = "/distributed";
		CountDownLatch countDownLatch = new CountDownLatch(1);
		InterProcessMutex lock = new InterProcessMutex(zkClient2, lockPath);
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					countDownLatch.await();
					lock.acquire();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("timestamp: " + System.currentTimeMillis() + ", thread: " + Thread.currentThread().getName());
				try {
					lock.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
		countDownLatch.countDown();
		TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        /*
            参考结果：
            timestamp: 1560839688861, thread: Thread-1
            timestamp: 1560839688887, thread: Thread-7
            timestamp: 1560839688894, thread: Thread-10
            timestamp: 1560839688899, thread: Thread-8
            timestamp: 1560839688906, thread: Thread-4
            timestamp: 1560839688909, thread: Thread-9
            timestamp: 1560839688914, thread: Thread-6
            timestamp: 1560839688923, thread: Thread-5
            timestamp: 1560839688927, thread: Thread-2
            timestamp: 1560839688930, thread: Thread-3
         */
	}
}
