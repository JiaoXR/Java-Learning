package com.jaxer.example.schema;

import com.alibaba.fastjson.JSON;
import com.jaxer.example.domain.Metric;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.util.Date;

/**
 * Created by jiaoxiangru on 3:06 PM 2019/1/22
 */
public class MetricSchema implements DeserializationSchema<Metric>, SerializationSchema<Metric> {

    @Override
    public Metric deserialize(byte[] bytes) {
        System.out.println(new Date() + ", deserialize-->" + new String(bytes));
        return JSON.parseObject(new String(bytes), Metric.class);
    }

    @Override
    public boolean isEndOfStream(Metric metricEvent) {
        return false;
    }

    @Override
    public byte[] serialize(Metric metricEvent) {
        return JSON.toJSONBytes(metricEvent);
    }

    @Override
    public TypeInformation<Metric> getProducedType() {
        return TypeInformation.of(Metric.class);
    }
}
