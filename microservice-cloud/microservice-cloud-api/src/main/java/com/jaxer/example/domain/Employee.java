package com.jaxer.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 员工类
 * <p>
 * Created by jaxer on 2018/10/30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true) //链式访问
public class Employee {
    private Integer id;
    private String name;
    private Integer age;
}
