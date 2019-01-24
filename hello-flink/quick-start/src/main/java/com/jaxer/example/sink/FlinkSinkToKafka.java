package com.jaxer.example.sink;

import com.jaxer.example.domain.Metric;
import com.jaxer.example.schema.MetricSchema;
import com.jaxer.example.util.ExecutionEnvUtil;
import com.jaxer.example.util.KafkaConfigUtil;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

/**
 * Created by jiaoxiangru on 6:21 PM 2019/1/24
 * 将数据写入 Kafka
 */
public class FlinkSinkToKafka {
    public static void main(String[] args) throws Exception {
        final ParameterTool parameterTool = ExecutionEnvUtil.createParameterTool(args);
        StreamExecutionEnvironment env = ExecutionEnvUtil.prepare(parameterTool);
        DataStreamSource<Metric> data = KafkaConfigUtil.buildSource(env);

        data.addSink(new FlinkKafkaProducer011<>(
                parameterTool.get("kafka.sink.brokers"),
                parameterTool.get("kafka.sink.topic"),
                new MetricSchema())
        ).name("flink-connectors-kafka").setParallelism(parameterTool.getInt("stream.sink.parallelism"));

        env.execute("flink learning connectors kafka");
    }
}
