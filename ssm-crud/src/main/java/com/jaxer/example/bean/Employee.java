package com.jaxer.example.bean;

import com.jaxer.example.util.DateUtil;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author jaxer
 * date 26/07/2018
 */
@Data
@ToString
public class Employee {
    private Integer id;

    private String name;

    private Short age;

    private Short gender;

    private Integer deptId;

    private Department department;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public String getCreateTime() {
        return DateUtil.formatDateTime(createTime);
    }

    public String getUpdateTime() {
        return DateUtil.formatDateTime(updateTime);
    }
}
