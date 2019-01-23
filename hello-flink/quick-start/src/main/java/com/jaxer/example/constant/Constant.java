package com.jaxer.example.constant;

/**
 * Created by jiaoxiangru on 12:56 PM 2019/1/22
 * 一些常量
 */
public class Constant {
    public static final String BROKER_LIST = "localhost:9092";
    // kafka TOPIC，Flink 程序中需要和这个统一
    public static final String KAFKA_TOPIC = "mytop";

    // MySQL
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
    public static final String MYSQL_USER = "root";
    public static final String MYSQL_PASSWORD = "123456";
}
