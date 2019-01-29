#  Apache Flink 学习笔记

- Apache Flink 是一个框架和分布式处理引擎，用于对无界（*unbounded*）和有界（*bounded*）数据流进行状态计算。
- Flink 设计为在所有常见的集群环境中运行，以内存速度和任何规模执行计算。



##  Process Unbounded and Bounded Data

###  Streaming

###  Batch

![](https://github.com/JiaoXR/Framework/blob/master/pics/flink/usecases-analytics.png)



## Levels of Abstraction

![](https://github.com/JiaoXR/Framework/blob/master/pics/flink/levels_of_abstraction.svg)



- [DataStream API](https://ci.apache.org/projects/flink/flink-docs-release-1.7/dev/datastream_api.html) (bounded/unbounded streams)
- [DataSet API](https://ci.apache.org/projects/flink/flink-docs-release-1.7/dev/batch/index.html) (bounded data sets)



## Programs and Dataflows

![](https://github.com/JiaoXR/Framework/blob/master/pics/flink/program_dataflow.svg)



###  Data Source

- 基于集合
- 基于文件
- 基于 Socket
- 自定义
  - 继承 *RichSourceFunction* 类



###  Data transformation

- Map
- FlatMap
- Filter
- KeyBy
- Reduce
- Aggregation
- Window
- WindowAll
- Union
- WindowJoin
- Split



### Data Sink

- Kafka, Elasticsearch, Socket, RabbitMQ, JDBC, Cassandra POJO、File、Print 等



## Parallel Dataflows

![](https://github.com/JiaoXR/Framework/blob/master/pics/flink/parallel_dataflow.svg)



##  Windows

![](https://github.com/JiaoXR/Framework/blob/master/pics/flink/windows.svg)



##  Time

- **Event Time** is the time when an event was created.
- **Ingestion time** is the time when an event enters the Flink dataflow at the source operator.
- **Processing Time** is the local time at each operator that performs a time-based operation.

![](https://github.com/JiaoXR/Framework/blob/master/pics/flink/event_ingestion_processing_time.svg)





PS: *UserBehavior.csv* 文件可以通过下面命令下载。

```bash
$ curl https://raw.githubusercontent.com/wuchong/my-flink-project/master/src/main/resources/UserBehavior.csv > UserBehavior.csv
```



> 官网：https://flink.apache.org/
>
> GitHub：https://github.com/apache/flink
>
> 参考链接：
>
> 1. http://www.54tianzhisheng.cn/tags/Flink/
> 2. http://wuchong.me/

