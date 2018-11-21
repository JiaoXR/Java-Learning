package com.jaxer.example.amqp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jaxer on 2018/11/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private Integer gender;
    private Integer age;
}
