# ZooKeeper安装及常用命令

### 1. 下载

下载地址：https://zookeeper.apache.org/

下载解压后，将文件夹移动到 `/usr/local` 目录下（以个人喜好而定）。

PS: 以 macOS 系统为例，ZooKeeper 版本为 3.4.14。



### 2. 配置文件

将 `/zookeeper-3.4.14/conf` 目录下的配置文件 `zoo_sample.cfg` 拷贝并重命名为 `zoo.cfg`。



配置文件内容如下：

```properties
# The number of milliseconds of each tick
tickTime=2000

# The number of ticks that the initial
# synchronization phase can take
initLimit=10

# The number of ticks that can pass between
# sending a request and getting an acknowledgement
syncLimit=5

# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just
# example sakes.
# 保存数据的目录，可自定义
dataDir=../data

# the port at which the clients will connect
clientPort=2181

# the maximum number of client connections.
# increase this if you need to handle more clients
#maxClientCnxns=60
#
# Be sure to read the maintenance section of the
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
#autopurge.snapRetainCount=3
# Purge task interval in hours
# Set to "0" to disable auto purge feature
#autopurge.purgeInterval=1
```

PS: 有关配置文件详情信息后面再行分析。



### 3. 一些常用命令

ZooKeeper 分为 Server 端和 Client 端，需要先启动 Server 端，再启动 Client 端。启动命令在 `/zookeeper-3.4.14/bin` 目录下。



- 启动 Server

```bash
$ ./zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /usr/local/zookeeper-3.4.14/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
```

- 验证 Server 是否启动

使用 `jps` 命令查看，若存在 QuorumPeerMain 进程，表示启动成功，例如：

```bash
$ jps
35851 Jps
35837 QuorumPeerMain
```

也可以使用 `./zkServer.sh status` 查看（此处为单机版）：

```bash
$ ./zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /usr/local/zookeeper-3.4.14/bin/../conf/zoo.cfg
Mode: standalone
```

- 启动客户端

```bash
$ ./zkCli.sh
# 启动成功后会打印一系列日志信息，如下：
Connecting to localhost:2181
# 还有很多...
[zk: localhost:2181(CONNECTED) 0]
```



### 4. 客户端节点操作命令

- 查看节点：ls {path}

```bash
# 查看根目录（/）下的子节点
[zk: localhost:2181(CONNECTED) 4] ls /
[zookeeper, dubbo, myZK]
```

查看详情：ls2 {path}

```bash
[zk: localhost:2181(CONNECTED) 32] ls2 /
[zookeeper, test, dubbo, hello, myZK]
cZxid = 0x0
ctime = Thu Jan 01 08:00:00 CST 1970
mZxid = 0x0
mtime = Thu Jan 01 08:00:00 CST 1970
pZxid = 0xcf
cversion = 25
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 0
numChildren = 5
```

- 创建节点：create {path} {content}

```bash
# 注意是"绝对路径"，而且必须要有值
[zk: localhost:2181(CONNECTED) 15] create /test test
Created /test

# 创建时加参数 -e 创建临时节点；加参数 -s 创建顺序节点
[zk: localhost:2181(CONNECTED) 22] create -e /test1 test1
Created /test1
```

- 查看节点信息：get 命令

```bash
[zk: localhost:2181(CONNECTED) 20] get /test
test
cZxid = 0xcb
ctime = Sat Jun 22 22:50:14 CST 2019
mZxid = 0xcc
mtime = Sat Jun 22 22:50:26 CST 2019
pZxid = 0xcb
cversion = 0
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 4
numChildren = 0
```

- 修改节点信息：set 命令

```bash
[zk: localhost:2181(CONNECTED) 18] set /test test
cZxid = 0xcb
ctime = Sat Jun 22 22:50:14 CST 2019
mZxid = 0xcc
mtime = Sat Jun 22 22:50:26 CST 2019
pZxid = 0xcb
cversion = 0
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 4
numChildren = 0
```

- 删除节点：delete

```bash
[zk: localhost:2181(CONNECTED) 26] delete /test1
```



参考链接：https://coolxing.iteye.com/blog/1871009