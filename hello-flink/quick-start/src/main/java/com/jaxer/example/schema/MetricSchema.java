package com.jaxer.example.schema;

import com.google.gson.Gson;
import com.jaxer.example.domain.Metric;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.nio.charset.Charset;

/**
 * Created by jiaoxiangru on 3:06 PM 2019/1/22
 */
public class MetricSchema implements DeserializationSchema<Metric>, SerializationSchema<Metric> {

    private static final Gson gson = new Gson();

    @Override
    public Metric deserialize(byte[] bytes) {
        return gson.fromJson(new String(bytes), Metric.class);
    }

    @Override
    public boolean isEndOfStream(Metric metricEvent) {
        return false;
    }

    @Override
    public byte[] serialize(Metric metricEvent) {
        return gson.toJson(metricEvent).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public TypeInformation<Metric> getProducedType() {
        return TypeInformation.of(Metric.class);
    }
}
