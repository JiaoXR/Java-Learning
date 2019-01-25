package com.jaxer.example.util;

import com.alibaba.fastjson.JSON;
import com.jaxer.example.domain.Metric;
import com.jaxer.example.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.jaxer.example.constant.Constant.*;

/**
 * 往 Kafka 写入数据
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
     * 向 Kafka 写入数据（测试）
     */
    private static void writeToKafka() {
        KafkaProducer<String, String> producer = getKafkaProducer();

        Metric metric = new Metric();
        metric.setTimestamp(System.currentTimeMillis());
        metric.setName("memory");
        Map<String, String> tags = new HashMap<>();
        Map<String, Object> fields = new HashMap<>();

        tags.put("cluster", "cluster");
        tags.put("host_ip", "127.0.0.1");

        fields.put("used_percent", 90d);
        fields.put("max", 27244873d);
        fields.put("used", 17244873d);
        fields.put("init", 27244873d);

        metric.setTags(tags);
        metric.setFields(fields);

        ProducerRecord<String, String> record = new ProducerRecord<>(
                KAFKA_TOPIC_MYTOP, null, null, JSON.toJSONString(metric)
        );
        producer.send(record);
        log.info("发送数据: " + JSON.toJSONString(metric));

        producer.flush();
    }

    private static KafkaProducer<String, String> getKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("key.serializer", SERIALIZER_KEY);
        props.put("value.serializer", SERIALIZER_VALUE);
        return new KafkaProducer<>(props);
    }
}
