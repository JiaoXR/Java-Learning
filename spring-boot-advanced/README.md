#  Spring Boot 常用组合

###  消息服务

- 主要作用：异步处理、应用解耦、流量削峰

- 类型

  - JMS (Java Message Service)

    Java 消息服务，基于 JVM 消息代理的规范。例如 ActiveMQ、HotnetMQ 等

  - AMQP (Advanced Message Queuing Protocol)

    高级消息消息队列协议，兼容 JMS。例如 RabbitMQ

- RabbitMQ

  - Message
  - Publisher
  - Exchange: 交换器，接收生产者发送的消息并路由给服务器中的队列。
    - direct: 点对点
    - fanout: 广播
    - topic: 选择匹配
    - hreaders
  - Queue
  - Binding: 消息队列和交换器之间关联；多对多。
  - Connection
  - Channel
  - Broker: 消息服务器

- docker 安装&启动

  - 安装&启动命令

  ```bash
  # 安装
  $  docker pull rabbitmq:3.7-rc-management
  
  # 启动
  $ docker run -d -p 5672:5672 -p 15672:15672 --name MyRabbitMQ 56cb0ad23da3
  ```

  - 验证是否成功

  打开 http://127.0.0.1:15672, 出现 RabbitMQ 登录界面（默认用户名和密码都是 `guest`）。

  - 1

###  Elasticsearch

> 官网地址：https://www.elastic.co/products/elasticsearch

- Elasticsearch 是一个分布式搜索服务，提供 Restful API，底层基于 Lucene，采用多 shard（分片）的方式保证数据安全。可以提供快速的存储、搜索和分析海量数据，是目前全文搜索引擎的首选。

- 数据结构

  索引、类型、文档、属性【类比 MySQL 的库、表、记录、字段】

- docker 本地启动命令：

```bash
$ docker run -e ES_JAVA_OPTS='-Xms256m -Xmx256m' -d -p 9200:9200 -p 9300:9300 --name ES01 5acf0e8da90b
```

验证是否启动成功：浏览器打开 http://127.0.0.1:9200/，若成功，则显示如下图：

![](https://github.com/JiaoXR/Framework/blob/master/pics/Elasticsearch.png)

- CRUD 实例

  - 存储数据

    - 请求地址：http://127.0.0.1:9200/megacorp/employee/3
    - 请求方式：PUT
    - 请求数据格式

    ```json
    {
        "first_name" :  "Douglas",
        "last_name" :   "Fir",
        "age" :         35,
        "about":        "I like to build cabinets",
        "interests":  [ "forestry" ]
    }
    ```

  - 检索数据

    - 请求地址：http://127.0.0.1:9200/megacorp/employee/3
    - 请求方式：GET
    -  返回数据格式

    ```json
    {
        "_index": "megacorp",
        "_type": "employee",
        "_id": "3",
        "_version": 1,
        "found": true,
        "_source": {
            "first_name": "Douglas",
            "last_name": "Fir",
            "age": 35,
            "about": "I like to build cabinets",
            "interests": [
                "forestry"
            ]
        }
    }
    ```

- Spring-data-elastic
  - https://github.com/spring-projects/spring-data-elasticsearch





