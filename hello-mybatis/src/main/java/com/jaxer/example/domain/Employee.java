package com.jaxer.example.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 实体类
 *
 * @author jaxer
 * date 16/08/2018
 */
@Data
@Alias("Employee") //指定别名
public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;
    private Integer status;
    private Integer deptId;
    private Date createTime;
    private Date updateTime;
}
