package com.jaxer.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 部门类
 * <p>
 * Created by jaxer on 2018/10/31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept {
    private Integer id;
    private String name;
    private Date createTime;
    private Date updateTime;
}
