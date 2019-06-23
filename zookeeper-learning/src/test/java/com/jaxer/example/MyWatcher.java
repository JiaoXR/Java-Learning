package com.jaxer.example;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代码千万行，注释第一行。
 * Created by jiaoxiangru on 09:58 2019-06-17
 * 自定义 Watcher
 */
public class MyWatcher implements Watcher {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperAsyncTest.class);

	public void process(WatchedEvent watchedEvent) {
		LOGGER.info("received watch event-->{}", watchedEvent.getPath());
	}
}
