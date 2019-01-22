package com.jaxer.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by jiaoxiangru on 12:43 PM 2019/1/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metric {
    public String name;
    public long timestamp;
    public Map<String, Object> fields;
    public Map<String, String> tags;
}
