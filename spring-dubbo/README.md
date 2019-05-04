#  Spring+Dubbo+Zookeeper 简单整合



RPC:  Remote Procedure Call.

RPC 框架：Dubbo、gRPC、Thrift、HSF 等



#### 注解

- 暴露服务：@Service
- 引用服务：@Reference



- docker zookeeper 启动命令：

```bash
$ docker run --name jxrZK -p 2181:2181 --restart always -d IMAGE ID
```



### 配置覆盖关系

1. 虚拟机参数（例如 -Ddubbo.protocol.port）
2. XML 配置（例如 <dubbo.protocol port=“20880">）
3. properties 配置（例如 dubbo.protocol.port=20880）



覆盖规则：

1. 方法优先，接口级次之，全局配置再次之（精确优先）；
2. 若级别一样，则消费方优先，提供方次之。



### 常用配置

timeout: 超时，默认 1000 毫秒。

reties: 重试次数。



- 多版本

- 本地存根



### 负载均衡

- RandomLoadBalance: 基于权重的随机负载均衡机制
- RoundRobinLoadBalance: 轮询
- LeastActiveLoadBalance: 最少活跃调用数
- ConsistentHashLoadBalance: 一致性 Hash，相同参数的请求总是发到同一提供者



### 服务降级

服务器压力剧增时，根据实际业务情况及流量，对一些服务和页面有策略的不处理或换种简单的方式处理，从而释放服务器资源以保障核心交易正常运行或高效运行。



### 集群容错

- Failover Cluster
- Failfast Cluster
- Failsafe Cluster
- Failback Cluster
- Forking Cluster
- Broadcast Cluster





> 参考链接：https://my.oschina.net/wangmengjun/blog/903967