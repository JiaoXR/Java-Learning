package com.jaxer.example.transform;

import com.alibaba.fastjson.JSON;
import com.jaxer.example.domain.Student;
import com.jaxer.example.util.CommonUtil;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.CoGroupedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import static com.jaxer.example.constant.Constant.KAFKA_TOPIC_STUDENT;

/**
 * Created by jiaoxiangru on 2:53 PM 2019/1/23
 * Data transform 各种操作
 */
public class Transform {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = CommonUtil.getProperties();

        SingleOutputStreamOperator<Student> student = env
                .addSource(new FlinkKafkaConsumer011<>(KAFKA_TOPIC_STUDENT, new SimpleStringSchema(), properties))
                .setParallelism(1)
                .map(string -> JSON.parseObject(string, Student.class)); //FastJson 解析字符串成 student 对象

        map(student);
//        flatMap(student);
//        filter(student);
//        keyBy(student);
//        reduce(student);
//        aggregation(student);

        env.execute("Flink add sink");
    }

    private static void project(DataStream<Student> dataStream) {

    }

    /**
     * Split: 根据条件将 DataStream 拆分为多个
     * Select: 从拆分的 DataStream 中选择指定的某一个或多个
     */
    private static void split(DataStream<Student> dataStream) {
        dataStream.split((OutputSelector<Student>) student -> {
            List<String> output = new ArrayList<>();
            if (student.getAge() % 2 == 0) {
                output.add("even");
            } else {
                output.add("odd");
            }
            return output;
        }).select("even", "odd");
    }

    /**
     * Window Join: ??
     */
    private static void windowJoin(DataStream<Student> dataStream) {
        dataStream.join(dataStream)
                .where((KeySelector<Student, Integer>) Student::getAge)
                .equalTo((KeySelector<Student, Integer>) Student::getId)
                .window(new WindowAssigner<CoGroupedStreams.TaggedUnion<Student, Student>, Window>() {
                    @Override
                    public Collection<Window> assignWindows(CoGroupedStreams.TaggedUnion<Student, Student> element, long timestamp, WindowAssignerContext context) {
                        return null;
                    }

                    @Override
                    public Trigger<CoGroupedStreams.TaggedUnion<Student, Student>, Window> getDefaultTrigger(StreamExecutionEnvironment env) {
                        return null;
                    }

                    @Override
                    public TypeSerializer<Window> getWindowSerializer(ExecutionConfig executionConfig) {
                        return null;
                    }

                    @Override
                    public boolean isEventTime() {
                        return false;
                    }
                })
                .apply((JoinFunction<Student, Student, Object>) (first, second) -> null);
    }

    /**
     * Union: 合并多个 DataStream
     */
    private static void union(DataStream<Student> dataStream) {
        dataStream.union(dataStream, dataStream);
    }

    /**
     * Window All: ??
     */
    private static void windowAll(DataStream<Student> dataStream) {
//        dataStream.keyBy(0).windowAll(Time.seconds(10));
    }

    /**
     * Window: ??
     */
    private static void window(DataStream<Student> dataStream) {
//        dataStream.keyBy(0).window(Time.seconds(10));
    }

    /**
     * 聚合操作：sum, min, max 等
     */
    private static void aggregation(DataStream<Student> dataStream) {
//        keyBy(dataStream).sum(0);
        keyBy(dataStream).sum("age");
    }

    // deprecated
//    private static void fold(DataStream<Student> dataStream) {
//        keyBy(dataStream).fold();
//    }

    /**
     * Reduce: ??
     */
    private static void reduce(DataStream<Student> dataStream) {
        keyBy(dataStream)
                .reduce((ReduceFunction<Student>) (value1, value2) -> {
                    Student student1 = new Student();
                    student1.setAge(value1.getAge() + value2.getAge());
                    return student1;
                })
                .print();
    }

    /**
     * KeyBy: ??
     */
    private static KeyedStream<Student, Integer> keyBy(DataStream<Student> dataStream) {
        return dataStream.keyBy((KeySelector<Student, Integer>) Student::getAge);
    }

    /**
     * Filter: 过滤指定条件的数据
     */
    private static void filter(DataStream<Student> dataStream) {
        dataStream.filter((FilterFunction<Student>) student -> student.getAge() < 30).print();
    }

    /**
     * FlatMap: 采用一条记录并输出零个，一个或多个记录
     */
    private static void flatMap(DataStream<Student> dataStream) {
        dataStream.flatMap(new FlatMapFunction<Student, Student>() {
            @Override
            public void flatMap(Student value, Collector<Student> out) {
                if (value.getAge() % 5 == 0) {
                    out.collect(value);
                }
            }
        }).print();

        // 可能不支持 lambda 表达式
//        dataStream.flatMap((FlatMapFunction<Student, Student>) (value, out) -> {
//            if (value.getAge() % 2 == 0) {
//                out.collect(value);
//            }
//        }).print();
    }

    /**
     * Map: 输入是一个数据流，输出的也是一个数据流
     */
    private static void map(DataStream<Student> dataStream) {
        dataStream.map((MapFunction<Student, Student>) value -> {
            Student s1 = new Student();
            s1.setName(value.getName());
            s1.setGender("女");
            s1.setAge(value.getAge() * 10);
            return s1;
        }).print();
    }
}
