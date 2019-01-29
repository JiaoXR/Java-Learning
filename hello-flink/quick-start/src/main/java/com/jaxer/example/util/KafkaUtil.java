package com.jaxer.example.util;

import com.alibaba.fastjson.JSON;
import com.jaxer.example.domain.Metric;
import com.jaxer.example.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.jaxer.example.constant.Constant.*;

/**
 * Kafka 常用操作
 * Created by jiaoxiangru on 12:45 PM 2019/1/22
 */
@Slf4j
public class KafkaUtil {

    public static void main(String[] args) throws InterruptedException {
        writeToKafka2();

        while (true) {
            writeToKafka();
            Thread.sleep(3000);
        }
    }

    public static Properties getProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "metric-group");
//        props.put("key.deserializer", DESERIALIZER_KEY);
//        props.put("value.deserializer", DESERIALIZER_VALUE);
        props.put("auto.offset.reset", "latest");
        return props;
    }

    public static Properties getProps(ParameterTool parameterTool) {
        Properties props = parameterTool.getProperties();
        props.put("bootstrap.servers", parameterTool.get(KAFKA_BROKERS));
        props.put("zookeeper.connect", parameterTool.get(KAFKA_ZOOKEEPER_CONNECT));
        props.put("group.id", parameterTool.get(KAFKA_GROUP_ID));
        props.put("key.deserializer", DESERIALIZER_KEY);
        props.put("value.deserializer", DESERIALIZER_VALUE);
        props.put("auto.offset.reset", "latest");
        return props;
    }

    /**
     * 向 Kafka 发送固定条数的数据（Student 类型）
     */
    private static void writeToKafka2() {
        KafkaProducer<String, String> producer = getKafkaProducer();

        for (int i = 0; i < 50; i++) {
            Student student = new Student(null, "name" + i, "女", 18 + i);
            ProducerRecord<String, String> record = new ProducerRecord<>(
                    KAFKA_TOPIC_STUDENT, null, null, JSON.toJSONString(student)
            );
            producer.send(record);
            log.info("发送数据: " + JSON.toJSONString(student));
        }

        producer.flush();
    }

    /**
     * 向 Kafka 写入数据（Metric 类型）
     */
    private static void writeToKafka() {
        KafkaProducer<String, String> producer = getKafkaProducer();
        String metricJsonString = getMetricJson();

        ProducerRecord<String, String> record = new ProducerRecord<>(
                KAFKA_TOPIC_MYTOP, null, null, metricJsonString
        );
        producer.send(record);
        log.info("发送数据: " + metricJsonString);

        producer.flush();
    }

    private static KafkaProducer<String, String> getKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("key.serializer", SERIALIZER_KEY);
        props.put("value.serializer", SERIALIZER_VALUE);
        return new KafkaProducer<>(props);
    }

    /**
     * 创建自定义数据类型并转为 JSON 字符串
     */
    private static String getMetricJson() {
        Metric metric = new Metric();
        metric.setTimestamp(System.currentTimeMillis());
        metric.setName("memory");

        Map<String, String> tags = new HashMap<>();
        tags.put("cluster", "cluster");
        tags.put("host_ip", "127.0.0.1");

        Map<String, Object> fields = new HashMap<>();
        fields.put("used_percent", 90d);
        fields.put("max", 27244873d);
        fields.put("used", 17244873d);
        fields.put("init", 27244873d);

        metric.setTags(tags);
        metric.setFields(fields);

        return JSON.toJSONString(metric);
    }
}
