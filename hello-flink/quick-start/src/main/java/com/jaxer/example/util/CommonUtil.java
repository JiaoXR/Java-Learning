package com.jaxer.example.util;

import java.util.Properties;

import static com.jaxer.example.constant.Constant.DESERIALIZER_KEY;
import static com.jaxer.example.constant.Constant.DESERIALIZER_VALUE;

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

}
