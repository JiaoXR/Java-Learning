package com.jaxer.example.util;

import com.jaxer.example.constant.Constant;
import com.jaxer.example.domain.Metric;
import com.jaxer.example.schema.MetricSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.internals.KafkaTopicPartition;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jiaoxiangru on 10:59 AM 2019/1/24
 */
public class KafkaConfigUtil {

    /**
     * 从 kafka 读取数据
     */
    public static DataStreamSource<Metric> buildSource(StreamExecutionEnvironment env) {
        ParameterTool parameter = (ParameterTool) env.getConfig().getGlobalJobParameters();
//        String topic = parameter.getRequired(Constant.KAFKA_TOPIC_MYTOP);
        String topic = Constant.KAFKA_TOPIC_MYTOP;
        Long time = parameter.getLong(Constant.CONSUMER_FROM_TIME, 0L);
        return buildSource(env, topic, time);
    }

    private static DataStreamSource<Metric> buildSource(StreamExecutionEnvironment env, String topic, Long time) {
        ParameterTool parameterTool = (ParameterTool) env.getConfig().getGlobalJobParameters();
        Properties props = KafkaUtil.getProps(parameterTool);
        FlinkKafkaConsumer011<Metric> consumer = new FlinkKafkaConsumer011<>(
                topic, new MetricSchema(), props
        );
        // 重置 offset 到 time 时刻
        if (time != 0L) {
            Map<KafkaTopicPartition, Long> partitionOffset = buildOffsetByTime(props, time);
            consumer.setStartFromSpecificOffsets(partitionOffset);
        }
        return env.addSource(consumer);
    }

    private static Map<KafkaTopicPartition, Long> buildOffsetByTime(Properties props, Long time) {
        props.setProperty("group.id", "query_time_" + time);

        Map<TopicPartition, Long> partitionMap = new HashMap<>();
        KafkaConsumer<TopicPartition, OffsetAndTimestamp> consumer = new KafkaConsumer<>(props);
        List<PartitionInfo> partitionInfoList = consumer.partitionsFor(Constant.KAFKA_TOPIC_MYTOP);
        partitionInfoList.forEach(e -> partitionMap.put(new TopicPartition(e.topic(), e.partition()), time));

        Map<KafkaTopicPartition, Long> partitionOffset = new HashMap<>();
        Map<TopicPartition, OffsetAndTimestamp> offsetResult = consumer.offsetsForTimes(partitionMap);
        offsetResult.forEach((key, value) -> partitionOffset.put(
                new KafkaTopicPartition(key.topic(), key.partition()), value.offset()
        ));

        consumer.close();
        return partitionOffset;
    }

}
