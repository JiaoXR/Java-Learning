package com.jaxer.example.kafka;

import com.alibaba.fastjson.JSON;
import com.jaxer.example.domain.Metric;
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
public class KafkaUtil {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            writeToKafka();
            Thread.sleep(3000);
        }
    }

    private static void writeToKafka() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); //key 序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); //value 序列化
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

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
                KAFKA_TOPIC, null, null, JSON.toJSONString(metric)
        );
        producer.send(record);
        System.out.println("发送数据: " + JSON.toJSONString(metric));

        producer.flush();
    }
}
