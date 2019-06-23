package com.jaxer.example;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 代码千万行，注释第一行。
 * Created by jiaoxiangru on 15:17 2019-06-17
 * ZK 客户端 Curator 操作测试
 */
public class CuratorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorTest.class);
    private static CuratorFramework zkClient2;

    @Before
    public void connect() {
        // 连接 ZK 服务器（两种方式）
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client1 = CuratorFrameworkFactory.newClient(ZKConstant.CONNECT_STRING, ZKConstant.SESSION_TIMEOUT, 3000, retryPolicy);
        client1.start();

        zkClient2 = CuratorFrameworkFactory.builder().connectString(ZKConstant.CONNECT_STRING)
                .sessionTimeoutMs(ZKConstant.SESSION_TIMEOUT).retryPolicy(retryPolicy).namespace("base").build();
        zkClient2.start();
    }

    @Test
    public void createNode() throws Exception {
        // 创建节点
        String path = "/lock";
        String s = zkClient2.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path, "init".getBytes());
        LOGGER.info("path-->{}", s);
    }

    @Test
    public void deleteNode() throws Exception {
        // 删除节点
        zkClient2.delete().deletingChildrenIfNeeded().withVersion(1).forPath("/lock");
    }

    @Test
    public void getData() throws Exception {
        // 获取节点数据（注意要加 Stat）
        Stat stat = new Stat();
        byte[] bytes = zkClient2.getData().storingStatIn(stat).forPath("/lock0000000003");
        LOGGER.info("data-->{}", new String(bytes));
    }

    @Test
    public void setData() throws Exception {
        String path = "/distributed";
        Stat stat = new Stat();
        zkClient2.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());

        byte[] bytes = zkClient2.getData().storingStatIn(stat).forPath(path);
        LOGGER.info("data-->{}", new String(bytes));
        // 更新数据（有版本号，CAS操作）
        Stat stat1 = zkClient2.setData().withVersion(stat.getVersion()).forPath(path, "hi".getBytes());
        LOGGER.info("stat1-->{}", stat1);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void watch() throws Exception {
        // 监听节点数据变化
        String path = "/watch";
        zkClient2.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());

        final NodeCache cache = new NodeCache(zkClient2, path, false);
        cache.start(true);
        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() {
                System.out.println("Node data update, new data: " + new String(cache.getCurrentData().getData()));
            }
        });
        // 修改节点数据
        zkClient2.setData().forPath(path, "u".getBytes());
        TimeUnit.SECONDS.sleep(2);

//        zkClient2.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void watchChildren() throws Exception {
        // 监听子节点
        String path = "/child";
        PathChildrenCache cache = new PathChildrenCache(zkClient2, path, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADDED, " + event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATED, " + event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED, " + event.getData().getPath());
                        break;
                    default:
                        break;
                }
            }
        });
        zkClient2.create().withMode(CreateMode.PERSISTENT).forPath(path);
        TimeUnit.SECONDS.sleep(1);

        zkClient2.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
        TimeUnit.SECONDS.sleep(1);

        zkClient2.delete().forPath(path + "/c1");
        TimeUnit.SECONDS.sleep(1);

        zkClient2.delete().forPath(path);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void leaderSelector() throws InterruptedException {
        // leader选举
        String masterPath = "/leader";
        LeaderSelector selector = new LeaderSelector(zkClient2, masterPath, new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("成为leader角色");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("完成leader操作，释放leader权利");
            }
        });
        selector.autoRequeue();
        selector.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void barrier() throws Exception {
        // 分布式 Barrier，类似 JDK 的 CyclicBarrier
        String barrierPath = "/barrier";
        AtomicReference<DistributedBarrier> distributedBarrier = new AtomicReference<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                distributedBarrier.set(new DistributedBarrier(zkClient2, barrierPath));
                System.out.println(Thread.currentThread().getName() + ": 设置 barrier");
                try {
                    distributedBarrier.get().setBarrier();
                    distributedBarrier.get().waitOnBarrier();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 启动");
            }).start();
        }
        TimeUnit.SECONDS.sleep(3);
        distributedBarrier.get().removeBarrier();

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
