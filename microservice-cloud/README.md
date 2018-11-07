#  Spring Cloud 小结

- Eureka

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

- Ribbon

基于 Netflix Ribbon 实现的一套客户端+负载均衡的工具。