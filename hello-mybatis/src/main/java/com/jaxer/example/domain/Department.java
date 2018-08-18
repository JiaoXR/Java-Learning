package com.jaxer.example.domain;

import lombok.Data;

import java.util.Date;

/**
 * 部门表
 *
 * @author jaxer
 * date 19/08/2018
 */
@Data
public class Department {
    private Integer id;
    private String deptName;
    private Date createTime;
    private Date updateTime;
}
