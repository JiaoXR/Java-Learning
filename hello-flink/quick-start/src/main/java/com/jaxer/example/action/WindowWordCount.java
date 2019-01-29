package com.jaxer.example.action;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * Created by jiaoxiangru on 4:19 PM 2019/1/28
 * 官网 DataStream 实例
 * https://ci.apache.org/projects/flink/flink-docs-release-1.7/dev/datastream_api.html#example-program
 */
public class WindowWordCount {
    public static void main(String[] args) throws Exception {
        // 创建 execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 通过连接 socket 获取输入数据，这里连接到本地 9000 端口（如果 9000 端口已被占用，请换一个端口）
        // 解析数据，按 word 分组，开窗，聚合
        DataStream<Tuple2<String, Integer>> dataStream = env
                .socketTextStream("localhost", 9000, "\n")
                .flatMap(new Splitter())
                .keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1);

        // 将结果打印到控制台，注意这里使用的是单线程打印，而非多线程
        dataStream.print().setParallelism(1);

        env.execute("Window WordCount");
    }

    private static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            // 注意 Tuple2 类不要导错
            for (String word : value.split("\\s")) {
                out.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
