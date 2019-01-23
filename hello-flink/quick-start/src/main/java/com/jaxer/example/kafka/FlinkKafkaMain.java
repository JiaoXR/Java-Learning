package com.jaxer.example.kafka;

import com.jaxer.example.constant.Constant;
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

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "metric-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  //key 反序列化
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); //value 反序列化
        props.put("auto.offset.reset", "latest");

        DataStreamSource<String> dataStreamSource = env.addSource(new FlinkKafkaConsumer011<>(
                Constant.KAFKA_TOPIC,  //kafka topic
                new SimpleStringSchema(),  // String 序列化
                props)
        ).setParallelism(1);

        //把从 kafka 读取到的数据打印在控制台
        dataStreamSource.print();

        env.execute("Flink add data source");
    }
}
