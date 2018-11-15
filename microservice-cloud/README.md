#  Spring Cloud 小结

- 微服务

微服务化的核心是将传统的一站式应用，根据业务拆分成一个个的服务，彻底地去耦合，每个微服务提供单个业务功能的服务，一个微服务做一件事，能够单独启动或销毁，拥有自己独立的数据库。

- Spring Cloud & Dubbo

| -            | Dubbo         | Spring Cloud                 |
| ------------ | ------------- | ---------------------------- |
| 服务注册中心 | ZooKeeper     | Spring Cloud Netflix Eureka  |
| 服务调用方式 | RPC           | REST API                     |
| 服务监控     | Dubbo-monitor | Spring Boot Admin            |
| 断路器       | 不完善        | Spring Cloud Netflix Hystrix |
| 服务网关     | 无            | Spring Cloud Netflix Zuul    |
| 分布式配置   | 无            | Spring Cloud Config          |
| 服务跟踪     | 无            | Spring Cloud Sleuth          |
| 消息总线     | 无            | Spring Cloud Bus             |
| 数据流       | 无            | Spring Cloud Stream          |
| 批量任务     | 无            | Spring Cloud Task            |

> 最大区别：Spring Cloud 抛弃了 Dubbo 的 RPC 通信，采用的是基于 HTTP 的 REST 方式。

- Eureka (服务注册与发现)

  Eureka 自我保护机制：若某时刻一个微服务不可用，eureka 仍会保存该微服务的信息，不立即清理。当收到心跳数重新恢复到阈值以上时，自动退出自我保护模式。（宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例）。

  - Eureka Server

  提供服务注册。各个节点启动后，会在 Eureka Server 中注册。

  - Eureka Client

  是一个 Java 客户端，用于简化 Eureka Server 交互，客户端也具备一个内置的、使用轮询（round-robin）负载算法的负载均衡器。在应用启动后将会向 Eureka Server 发送心跳（默认周围为 30 秒），若多个周期没有收到某个节点的心跳，Eureka Server 会将该节点从服务注册表中移除。

- SQL
  - RSBMS(Relational Database Management System): MySQL, Oracle, SQLServer 等：ACID
    - A: Atomic, 原子性；
    - C: Consistency, 一致性；
    - I: Isolation, 隔离性；
    - D: Durability, 持久性；
  - NoSQL: Redis, MongDB 等：CAP
    - C: Consistency, 一致性；
    - A: Availability, 可用性；
    - P: Partition Tolerance, 分区容错性；

- Eureka & ZooKeeper
  - Eureka: 保证 AP
  - ZooKeeper: 保证 CP
  - Eureka 可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像 ZooKeeper 那样使得整个服务瘫痪。

- Ribbon (负载均衡)

基于 Netflix Ribbon 实现的一套客户端+负载均衡的工具。

```java
//开启负载均衡
@LoadBalanced

//自定义负载均衡算法
@RibbonClient(name = "MICROSERVICE-CLOUD-DEPT", configuration = MyRule.class)

//负载均衡算法接口
public interface IRule {
	//...  
}
```

- Feign (负载均衡)

“接口 + 注解”的客户端负载均衡工具。

```java
@FeignClient(value = "MICROSERVICE-CLOUD-DEPT")

@EnableFeignClients
@ComponentScan
```

- Hystrix (断路器)

用于处理分布式系统的延迟和容错。保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，提高分布式系统的弹性（类似保险丝）。

- 服务降级

整体资源紧缺时，暂时将某些服务关闭，待资源充足时再开启。一般从整体负荷考虑。

```java
// 实现接口 
import feign.hystrix.FallbackFactory;

@Component
public class DeptClientServiceFallback implements FallbackFactory<DeptClientService> {}
```

- Hystrix Dashboard (可视化微服务监控)

准实时的调用监控

```java
@EnableHystrixDashboard
```

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
	<version>${starter.version}</version>
</dependency>
```

http://localhost:8001/hystrix.stream

404 ??

- Zuul (服务网关)
  - 包含了对请求的路由和过滤两个最重要的功能。
  - Zuul 和 Eureka 整合，将 Zuul自身注册为 Eureka 服务治理下的应用，同时从 Eureka 中获得其他微服务的消息，即，以后的访问微服务都是通过 Zuul 跳转后获得。
- 通过网关 Zuul 访问微服务
  - 原始：http://myzuul.com:9527/microservice-cloud-dept/dept/list
  - 添加映射(映射为 `mydept` )：http://myzuul.com:9527/mydept/dept/2
  - 添加前缀(前缀为 `ace`)：http://myzuul.com:9527/ace/mydept/dept/2

注意微服务名大小写！

- Config (分布式配置)
  - 通过 Config Server 访问远程配置
    - http://config-3344.com:3344/application-dev.yml
    - http://config-3344.com:3344/application-other.yml
  - Client 通过 Config Server 访问远程配置
    - http://client-config.com:8201/config
    - http://client-config.com:8202/config