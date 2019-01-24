package com.jaxer.example.util;

import org.apache.flink.api.java.utils.ParameterTool;

import java.util.Properties;

import static com.jaxer.example.constant.Constant.*;

/**
 * Created by jiaoxiangru on 3:00 PM 2019/1/23
 */
public class CommonUtil {

    public static Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "metric-group");
        props.put("key.deserializer", DESERIALIZER_KEY);
        props.put("value.deserializer", DESERIALIZER_VALUE);
        props.put("auto.offset.reset", "latest");
        return props;
    }

    public static Properties buildKafkaProps(ParameterTool parameterTool) {
        Properties props = parameterTool.getProperties();
        props.put("bootstrap.servers", parameterTool.get(KAFKA_BROKERS));
        props.put("zookeeper.connect", parameterTool.get(KAFKA_ZOOKEEPER_CONNECT));
        props.put("group.id", parameterTool.get(KAFKA_GROUP_ID));
        props.put("key.deserializer", DESERIALIZER_KEY);
        props.put("value.deserializer", DESERIALIZER_VALUE);
        props.put("auto.offset.reset", "latest");
        return props;
    }

}
