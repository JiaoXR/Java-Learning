package com.jaxer.example;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 代码千万行，注释第一行。
 * Created by jiaoxiangru on 09:53 2019-06-17
 * ZooKeeper 异步操作测试
 */
public class ZooKeeperAsyncTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperAsyncTest.class);
    private static ZooKeeper zooKeeper;

    @Before
    public void connect() throws IOException {
        // 链接ZK服务端
        zooKeeper = new ZooKeeper(ZKConstant.CONNECT_STRING, ZKConstant.SESSION_TIMEOUT, new MyWatcher());
        LOGGER.info("zookeeper state-->" + zooKeeper.getState());
    }

    @Test
    public void createZNode() throws InterruptedException {
        // 异步创建节点(临时)
        zooKeeper.create("/zk-ephemeral", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new MyAsyncCallback(), "this is context");

        // 异步创建节点(临时有序)
        zooKeeper.create("/zk-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
                new MyAsyncCallback(), "this is context");

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    private static class MyAsyncCallback implements AsyncCallback.StringCallback {
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("Create path result: [" + rc + ", " + path + ", " + ctx + ", real path name: " + name);
        }
    }
}
