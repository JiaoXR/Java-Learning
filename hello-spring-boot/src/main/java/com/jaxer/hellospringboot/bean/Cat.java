package com.jaxer.hellospringboot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 使用 @PropertySource 加载指定配置文件
 * Created by jaxer on 23/09/2018
 */
@Data
@Component
@PropertySource(value = {"classpath:cat.yml"})
@ConfigurationProperties(prefix = "cat")
public class Cat {
    private String name;
    private Integer age;
}
