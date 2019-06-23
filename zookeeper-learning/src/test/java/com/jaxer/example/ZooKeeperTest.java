package com.jaxer.example;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 代码千万行，注释第一行。
 * Created by jiaoxiangru on 16:26 2019-06-14
 * ZooKeeper API 测试
 */
public class ZooKeeperTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperTest.class);
	private static ZooKeeper zooKeeper;

	@Before
	public void connect() throws IOException {
		// 链接ZK服务端
		zooKeeper = new ZooKeeper(ZKConstant.CONNECT_STRING, ZKConstant.SESSION_TIMEOUT, new MyWatcher());
		LOGGER.info("zookeeper state-->" + zooKeeper.getState());
	}

	@Test
	public void createZNode() throws KeeperException, InterruptedException {
		// 创建节点(临时)
		String path1 = zooKeeper.create("/zk-ephemeral", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		LOGGER.info("path-->{}", path1);
		// 创建节点(临时有序)
		String path2 = zooKeeper.create("/zk-ephemeral-", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		LOGGER.info("path-->{}", path2);
//        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
	}

	@Test
	public void getNode() throws KeeperException, InterruptedException {
		// 获取节点数据(节点不存在时会抛出异常：KeeperException$NoNodeException)
		byte[] data = zooKeeper.getData("/hello", new MyWatcher(), null);
		LOGGER.info("data-->{}", new String(data));

		// 获取子节点
		List<String> children = zooKeeper.getChildren("/", true);
		LOGGER.info("children-->{}", children);
	}

	@Test
	public void setNodeData() throws KeeperException, InterruptedException {
		// 更新节点数据（有版本号）
		Stat stat = zooKeeper.setData("/hello", "test".getBytes(), -1);
		LOGGER.info("stat-->{}", stat);
		Stat stat1 = zooKeeper.setData("/hello", "test2".getBytes(), stat.getVersion());
		LOGGER.info("stat1-->{}", stat1);
	}

	@Test
	public void isNodeExists() throws KeeperException, InterruptedException {
		// 判断节点是否存在（存在时返回状态，否则返回null）
		Stat exists = zooKeeper.exists("/hi", true);
		LOGGER.info("exists-->{}", exists);
	}

	@Test
	public void acl() throws KeeperException, InterruptedException, IOException {
		// 权限控制
		zooKeeper.addAuthInfo("digest", "ha".getBytes());
		String s = zooKeeper.create("/hi", "hi".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
		LOGGER.info("path-->{}", s);

		// 抛出异常：KeeperException$NoAuthException
		ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, null);
		// 添加权限后可访问
//        zooKeeper.addAuthInfo("digest", "ha".getBytes());
		byte[] data = zooKeeper.getData("/hi", true, null);
		LOGGER.info("data-->{}", new String(data));
	}

	@Test
	public void deleteNode() throws KeeperException, InterruptedException {
		// 删除节点
		// 则无法将其删除，必须先删除其所有子节点
		zooKeeper.delete("/myZK", -1);
	}
}
