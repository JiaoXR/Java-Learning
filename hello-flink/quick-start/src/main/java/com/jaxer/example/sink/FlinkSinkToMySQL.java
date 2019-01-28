package com.jaxer.example.sink;

import com.alibaba.fastjson.JSON;
import com.jaxer.example.domain.Student;
import com.jaxer.example.util.KafkaUtil;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.Properties;

import static com.jaxer.example.constant.Constant.KAFKA_TOPIC_STUDENT;

/**
 * Created by jiaoxiangru on 1:49 PM 2019/1/23
 */
public class FlinkSinkToMySQL {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = KafkaUtil.getProps();

        SingleOutputStreamOperator<Student> student = env
                .addSource(new FlinkKafkaConsumer011<>(KAFKA_TOPIC_STUDENT, new SimpleStringSchema(), properties))
                .setParallelism(1)
                .map(string -> JSON.parseObject(string, Student.class)); //FastJson 解析字符串成 student 对象

        student.addSink(new SinkToMySQL()); //数据 sink 到 mysql

        env.execute("Flink add sink");
    }
}
