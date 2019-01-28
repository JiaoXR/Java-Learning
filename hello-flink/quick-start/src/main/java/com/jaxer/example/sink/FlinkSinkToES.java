package com.jaxer.example.sink;

import com.alibaba.fastjson.JSON;
import com.jaxer.example.domain.Metric;
import com.jaxer.example.util.ExecutionEnvUtil;
import com.jaxer.example.util.KafkaConfigUtil;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.List;

import static com.jaxer.example.constant.Constant.*;

/**
 * Created by jiaoxiangru on 10:48 AM 2019/1/24
 */
public class FlinkSinkToES {
    public static void main(String[] args) throws Exception {
        final ParameterTool parameterTool = ExecutionEnvUtil.createParameterTool(args);
        StreamExecutionEnvironment env = ExecutionEnvUtil.prepare(parameterTool);
        DataStream<Metric> dataStream = KafkaConfigUtil.buildSource(env);

        List<HttpHost> esHostList = SinkToElasticsearch.getEsAddresses(parameterTool.get(ELASTICSEARCH_HOSTS));
        // 从配置文件中读取 bulk flush size，代表一次批处理的数量，这个可是性能调优参数
        int bulkSize = parameterTool.getInt(ELASTICSEARCH_BULK_FLUSH_MAX_ACTIONS, 40);
        // 从配置文件中读取并行 sink 数，这个也是性能调优参数，能够更快的消费，防止 kafka 数据堆积
        int sinkParallelism = parameterTool.getInt(STREAM_SINK_PARALLELISM, 5);

        // 自己再自带的 es sink 上一层封装了下
        SinkToElasticsearch.addSink(esHostList, bulkSize, sinkParallelism, dataStream,
                (Metric metric, RuntimeContext runtimeContext, RequestIndexer requestIndexer) ->
                        requestIndexer.add(Requests.indexRequest()
                                .index(ELASTIC_SEARCH_INDEX)  //es index
                                .type(ELASTIC_SEARCH_TYPE) //es type
                                .source(JSON.toJSONBytes(metric), XContentType.JSON)));

        env.execute("flink learning connectors es6");
    }
}
