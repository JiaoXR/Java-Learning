package com.jaxer.example.source.kafka;

import com.jaxer.example.constant.Constant;
import com.jaxer.example.domain.Metric;
import com.jaxer.example.schema.MetricSchema;
import com.jaxer.example.util.KafkaUtil;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;

/**
 * Created by jiaoxiangru on 2:51 PM 2019/1/22
 * 从 Kafka 读取数据
 */
public class FlinkKafkaMain {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = KafkaUtil.getProps();

        env.setParallelism(1);
        DataStream<Metric> dataStream = env
                .addSource(new FlinkKafkaConsumer011<>(Constant.KAFKA_TOPIC_MYTOP, new MetricSchema(), properties));

        // 打印读取到的数据
//        dataStream.print();

        // 增加 Watermark
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        dataStream
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Metric>() {
                    @Override
                    public long extractAscendingTimestamp(Metric metric) {
                        return metric.timestamp;
                    }
                })
                .keyBy("timestamp")
                .print();

        env.execute("Flink add data source");
    }
}
