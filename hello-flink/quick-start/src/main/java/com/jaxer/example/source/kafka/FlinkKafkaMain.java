package com.jaxer.example.source.kafka;

import com.jaxer.example.constant.Constant;
import com.jaxer.example.util.CommonUtil;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;

/**
 * Created by jiaoxiangru on 2:51 PM 2019/1/22
 * 从 Kafka 读取数据
 */
public class FlinkKafkaMain {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = CommonUtil.getProperties();

        DataStreamSource<String> dataStreamSource = env
                .addSource(new FlinkKafkaConsumer011<>(Constant.KAFKA_TOPIC_MYTOP, new SimpleStringSchema(), properties))
                .setParallelism(1);

        //把从 kafka 读取到的数据打印在控制台
        dataStreamSource.print();

        env.execute("Flink add data source");
    }
}
