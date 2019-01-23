package com.jaxer.example.source.sql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Created by jiaoxiangru on 3:57 PM 2019/1/22
 * Flink 从 MySQL 读取数据
 */
public class FlinkMySQLMain {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.addSource(new SourceMySQL()).print();

        env.execute("Flink add data source");
    }
}
