package com.jaxer.example.bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author jaxer
 * date 26/07/2018
 */
@Data
@ToString
public class Department {
    private Integer id;
    private String name;
    private Date createTime;
}
