package com.jaxer.hellospringboot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 测试 yml 属性配置
 * <p>
 * Created by jaxer on 23/09/2018
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private String age;

    private Map<String, Object> map;
    private List<String> list;
}
