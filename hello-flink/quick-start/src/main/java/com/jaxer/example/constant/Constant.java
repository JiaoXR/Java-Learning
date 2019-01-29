package com.jaxer.example.constant;

/**
 * Created by jiaoxiangru on 12:56 PM 2019/1/22
 * 一些常量
 */
public class Constant {
    // kafka
    public static final String BROKER_LIST = "localhost:9092";
    public static final String KAFKA_TOPIC_MYTOP = "mytop";
    public static final String KAFKA_TOPIC_STUDENT = "student";

    // MySQL
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
    public static final String MYSQL_USER = "root";
    public static final String MYSQL_PASSWORD = "123456";

    // Elasticsearch
    public static final String ELASTIC_SEARCH_INDEX = "sink_from_flink";
    public static final String ELASTIC_SEARCH_TYPE = "flink_type";

    // 序列化 & 反序列化
    public static final String SERIALIZER_KEY = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String SERIALIZER_VALUE = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String DESERIALIZER_KEY = "org.apache.kafka.common.serialization.StringDeserializer";
    public static final String DESERIALIZER_VALUE = "org.apache.kafka.common.serialization.StringDeserializer";

    // 配置
    public static final String KAFKA_BROKERS = "kafka.brokers";
    public static final String KAFKA_ZOOKEEPER_CONNECT = "kafka.zookeeper.connect";
    public static final String KAFKA_GROUP_ID = "kafka.group.id";
    public static final String CONSUMER_FROM_TIME = "consumer.from.time";
    public static final String STREAM_PARALLELISM = "stream.parallelism";
    public static final String STREAM_SINK_PARALLELISM = "stream.sink.parallelism";
    public static final String STREAM_CHECKPOINT_ENABLE = "stream.checkpoint.enable";
    public static final String STREAM_CHECKPOINT_INTERVAL = "stream.checkpoint.interval";
    public static final String PROPERTIES_FILE_NAME = "/application.properties";
    // elasticsearch config
    public static final String ELASTICSEARCH_BULK_FLUSH_MAX_ACTIONS = "elasticsearch.bulk.flush.max.actions";
    public static final String ELASTICSEARCH_HOSTS = "elasticsearch.hosts";
}
