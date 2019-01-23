#  Flink 学习小结

## 0. 准备工作

###  0.1  安装 Zookeeper

- 查看&安装 Zookeeper

```bash
# 查看 Zookeeper 信息
$ brew info zookeeper

# 安装 Zookeeper
$ brew install zookeeper
```

- Zookeeper 安装目录： `/usr/local/Cellar/zookeeper/`
- 默认配置文件（四个）在 `/usr/local/etc/zookeeper/` 目录下，分别为：
  - defaults
  - log4j.properties
  - zoo.cfg
  - zoo_sample.cfg
- 启动 Zookeeper
  - 方式 1
    - 所在目录：`/usr/local/Cellar/zookeeper/3.4.13/bin`
    - 命令：`./zkServer start /usr/local/etc/zookeeper/zoo.cfg`
  - 方式 2
    - 所在目录：`/usr/local/Cellar/zookeeper/3.4.13/libexec/bin`
    - 命令：`./zkServer.sh start /usr/local/etc/zookeeper/zoo.cfg`
  - ![](https://github.com/JiaoXR/Framework/blob/master/pics/flink/zk_start.png)
- 查看状态
  - 所在目录：`/usr/local/Cellar/zookeeper/3.4.13/bin`
  - 命令：`zkServer status`
  - ![](https://github.com/JiaoXR/Framework/blob/master/pics/flink/zk_status.png)
- 停止
  - 所在目录：`/usr/local/Cellar/zookeeper/3.4.13/bin`
  - 命令：`./zkServer stop`

###  0.2  安装 Kafka

- 安装命令

```bash
$ brew install kafka
```

- 安装目录：`/usr/local/Cellar/kafka/2.1.0/`
- 配置文件
  - 目录：`/usr/local/etc/kafka`
  - 配置文件（较多）
    - connect-console-sink.properties
    - connect-console-source.properties
    - connect-distributed.properties
    - connect-file-sink.properties
    - connect-file-source.properties
    - connect-log4j.properties
    - connect.standalone.properties
    - consumer.properties
    - log4j.properties
    - producer.properties
    - server.properties
    - tools-log4.properties
    - trogdor.conf
    - zookeeper.
- 启动 Kafka
  - 目录：`/usr/local/Cellar/kafka/2.1.0/libexec/bin`
  - 命令：`./kafka-server-start.sh /usr/local/etc/kafka/server.properties`
  - 启动前注意
    - 需要先启动 Zookeeper；
    - 需要修改 server.properties 文件，增加：`listeners=PLAINTEXT://localhost:9092`
    - PS: 此处是本地运行。
- 查看 topic

```bash
$ ./kafka-topics --list --zookeeper localhost:2181
```

- 启动生产者

```bash
$ ./kafka-console-producer --topic mytop --broker-list localhost:9092
```

- 启动消费者

```bash
$ ./kafka-console-consumer --bootstrap-server localhost:9092 -topic mytop
```

- 测试

生产者和消费者都启动后，在「生产者」终端输入消息并回车，可以在「消费者」终端打印出来，测试通过。

###  0.3  安装 Flink

- 安装命令

```bash
$ brew install apache-flink
```

- 启动
  - 目录：`/usr/local/Cellar/apache-flink/1.7.1/libexec/bin`
  - 命令：`$ ./start-cluster.sh`

##  1. Flink 学习



> 官网连接：https://flink.apache.org/
>
> GitHub：https://github.com/apache/flink





自定义 Data Source：Kafka、MySQL 等。

Data Sink: Kafka, Elasticsearch, Socket, RabbitMQ, JDBC, Cassandra POJO、File、Print 等 Sink 的方式。









> 参考链接：http://www.54tianzhisheng.cn/tags/Flink/



##  2. 遇到问题

- `java.lang.ClassNotFoundException: org.apache.kafka.clients.producer.KafkaProducer`

解决方法：引入 kafka-clients, 如下：

```xml
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>${kafka.version}</version>
</dependency>
```

- `java.lang.ClassNotFoundException: org.apache.flink.api.common.serialization.DeserializationSchema`

详情如下：

```bash
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/flink/api/common/serialization/DeserializationSchema
	at java.lang.Class.getDeclaredMethods0(Native Method)
	at java.lang.Class.privateGetDeclaredMethods(Class.java:2701)
	at java.lang.Class.privateGetMethodRecursive(Class.java:3048)
	at java.lang.Class.getMethod0(Class.java:3018)
	at java.lang.Class.getMethod(Class.java:1784)
	at sun.launcher.LauncherHelper.validateMainClass(LauncherHelper.java:544)
	at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:526)
Caused by: java.lang.ClassNotFoundException: org.apache.flink.api.common.serialization.DeserializationSchema
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	... 7 more
```

原来是 \<dependency> 中 \<scope> 的问题：之前设置的 `<scope>provided</scope>`，不知道在哪看的，也并不很清楚 \<scope> 的含义。将其删掉即可。

> 有关 scope 的含义，可参考：https://stackoverflow.com/questions/26975818/what-is-scope-under-dependency-in-pom-xml-for

