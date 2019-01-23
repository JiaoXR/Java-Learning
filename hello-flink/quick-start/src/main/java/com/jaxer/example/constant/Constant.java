package com.jaxer.example.constant;

/**
 * Created by jiaoxiangru on 12:56 PM 2019/1/22
 * 一些常量
 */
public class Constant {
    public static final String BROKER_LIST = "localhost:9092";
    // kafka TOPIC，Flink 程序中需要和这个统一
    public static final String KAFKA_TOPIC_MYTOP = "mytop";
    public static final String KAFKA_TOPIC_STUDENT = "student";

    // MySQL
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
    public static final String MYSQL_USER = "root";
    public static final String MYSQL_PASSWORD = "123456";

    // 序列化 & 反序列化
    public static final String SERIALIZER_KEY = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String SERIALIZER_VALUE = "org.apache.kafka.common.serialization.StringSerializer";

    public static final String DESERIALIZER_KEY = "org.apache.kafka.common.serialization.StringDeserializer";
    public static final String DESERIALIZER_VALUE = "org.apache.kafka.common.serialization.StringDeserializer";

}
