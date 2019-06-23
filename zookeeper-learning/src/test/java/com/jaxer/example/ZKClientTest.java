package com.jaxer.example;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 代码千万行，注释第一行。
 * Created by jiaoxiangru on 18:05 2019-06-14
 * 测试ZK客户端操作
 */
public class ZKClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKClientTest.class);
    private static ZkClient zkClient;

    @Before
    public void connect() {
        // 创建客户端（连接ZK服务器 ）
        zkClient = new ZkClient(ZKConstant.CONNECT_STRING, ZKConstant.SESSION_TIMEOUT);
        System.out.println("zkClient-->" + zkClient.getChildren("/"));
    }

    @Test
    public void createNode() {
        // 递归创建节点（先创建父节点，再创建子节点）
        String path = "/hello/world";
        zkClient.createPersistent(path, true);
        Object data = zkClient.readData(path);
        LOGGER.info("data-->{}", data);
    }

    @Test
    public void readData() {
        // 读写数据
        String path = "/myZK";
        zkClient.createPersistent(path);
        zkClient.writeData(path, "zk");

        String data = zkClient.readData(path);
        LOGGER.info("data-->{}", data);
    }

    @Test
    public void isNodeExists() {
        // 节点是否存在
        boolean exists = zkClient.exists("/");
        LOGGER.info("exists-->{}", exists);
    }

    @Test
    public void deleteNode() {
        // 递归删除节点
        String path = "/hello/world";
        boolean recursive = zkClient.deleteRecursive(path);
        if (recursive) {
            Object data = zkClient.readData(path);
            LOGGER.info("data-->{}", data);
        }
    }

    @Test
    public void subscribe() throws InterruptedException {
        // 监控节点变化（此处为监控根节点下面子节点的变化）
        String path = "/";
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> currentChilds) {
                LOGGER.info("parentPath-->{}, currentChilds-->{}", parentPath, currentChilds);
            }
        });
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
