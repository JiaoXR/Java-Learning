package com.jaxer.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by jiaoxiangru on 12:43 PM 2019/1/22
 * Kafka 数据模型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metric {
    public String name;
    public Long timestamp;
    public Map<String, Object> fields;
    public Map<String, String> tags;
}
