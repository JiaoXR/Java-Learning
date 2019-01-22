package com.jaxer.example.elastic.domain;

import io.searchbox.annotations.JestId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jaxer on 2018/11/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JestId
    private Integer id;
    private String name;
    private Integer age;
    private String birthday;
}
