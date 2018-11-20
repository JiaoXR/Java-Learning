#  Spring Boot 常用组合



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

![]()

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





