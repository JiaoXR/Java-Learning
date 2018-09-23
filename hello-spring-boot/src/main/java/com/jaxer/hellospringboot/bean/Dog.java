package com.jaxer.hellospringboot.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用 @Value 读取配置内容
 * Created by jaxer on 23/09/2018
 */
@Data
@Component
public class Dog {
    @Value("${dog.name}")
    private String name;
    @Value("#{2*2}")
    private Integer age;
    @Value("${dog.gender}")
    private Integer gender;
}
