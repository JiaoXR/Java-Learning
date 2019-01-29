#  DataStream & DataSet



##  DataStream

官网代码实例：

[WindowWordCount.java](https://github.com/JiaoXR/Framework/blob/master/hello-flink/quick-start/src/main/java/com/jaxer/example/action/WindowWordCount.java)



###  Data Sources

There are several predefined stream sources accessible from the `StreamExecutionEnvironment`:

####  1. File-based

- readTextFile(filePath)
- readTextFile(filePath, charsetName)

####  2. Socket-based

- socketTextStream

####  3. Collection-based

- fromCollection(Collection)
- fromCollection(Iterator, Class)
- fromElements
- fromParallelCollection
- generateSequence

####  4. Custom

*addSource()*

- Apache Kafka

  - pom.xml

    ```xml
    <dependency>
    	<groupId>org.apache.flink</groupId>
    	<artifactId>flink-connector-kafka-0.11_2.12</artifactId>
    	<version>${flink.version}</version>
    </dependency>
    ```

  - 示例代码

    [FlinkKafkaMain.java](https://github.com/JiaoXR/Framework/blob/master/hello-flink/quick-start/src/main/java/com/jaxer/example/source/kafka/FlinkKafkaMain.java)

- 其他 Source

  https://ci.apache.org/projects/flink/flink-docs-release-1.7/dev/connectors/index.html

  ![connectors](https://github.com/JiaoXR/Framework/blob/master/pics/flink/connectors.png)

###  DataSet Transformations

*Map*, *FlatMap*, *Filter* 等操作。

> 详见 Operators：
>
> https://ci.apache.org/projects/flink/flink-docs-release-1.7/dev/stream/operators/index.html

###  Data Sinks

####  1. writeAsText

####  2. writeAsCsv

####  3. print

####  4. writeUsingOutputFormat

####  5. writeToSocket

####  6. addSink

- Apache Kafka

  - 示例代码

    [FlinkSinkToKafka.java](https://github.com/JiaoXR/Framework/blob/master/hello-flink/quick-start/src/main/java/com/jaxer/example/sink/FlinkSinkToKafka.java)



##  DataSet

Data transformations transform one or more DataSets into a new DataSet. Programs can combine multiple transformations into sophisticated assemblies.



官网代码实例：

[WordCountExample.java](https://github.com/JiaoXR/Framework/blob/master/hello-flink/quick-start/src/main/java/com/jaxer/example/action/WordCountExample.java)



###  DataSet Transformations

[transformations documentation](https://ci.apache.org/projects/flink/flink-docs-release-1.7/dev/batch/dataset_transformations.html)

