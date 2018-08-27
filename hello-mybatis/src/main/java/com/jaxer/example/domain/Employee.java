package com.jaxer.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author jaxer
 * date 16/08/2018
 */
@Data
@NoArgsConstructor
@Alias("Employee") //指定别名
public class Employee implements Serializable {
    private static final long serialVersionUID = 3634104982730363724L;
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;
    private Integer status;
    private Integer deptId;
    private Date createTime;
    private Date updateTime;
    private Department dept;

    public Employee(Integer id, String name, Integer age, Integer gender, Integer deptId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.deptId = deptId;
    }
}
