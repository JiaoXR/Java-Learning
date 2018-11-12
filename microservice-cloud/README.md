#  Spring Cloud 小结

- Eureka (服务注册与发现)

Eureka 自我保护机制：若某时刻一个微服务不可用，eureka 仍会保存该微服务的信息，不立即清理。当收到心跳数重新恢复到阈值以上时，自动退出自我保护模式。（宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例）。

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

- Hystrix Dashboard

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

- Zuul
  - 包含了对请求的路由和过滤两个最重要的功能。
  - Zuul 和 Eureka 整合，将 Zuul自身注册为 Eureka 服务治理下的应用，同时从 Eureka 中获得其他微服务的消息，即，以后的访问微服务都是通过 Zuul 跳转后获得。
- 通过网关 Zuul 访问微服务
  - 原始：http://muzuul.com:9527/microservice-cloud-dept/dept/list
  - 添加映射(映射为 `mydept` )：http://muzuul.com:9527/mydept/dept/2
  - 添加前缀(前缀为 `ace`)：http://muzuul.com:9527/ace/mydept/dept/2

注意微服务名大小写！





