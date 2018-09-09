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
public class Department {
    private Integer id;
    private String name;
    private LocalDateTime createTime;

    public String getCreateTime() {
        return DateUtil.formatDateTime(createTime);
    }
}
