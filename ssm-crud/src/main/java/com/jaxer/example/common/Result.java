package com.jaxer.example.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回信息
 * <p>
 * Created by jaxer on 08/09/2018
 */
@Data
public class Result {
    private Integer code; //状态码
    private String msg; //状态信息
    private Map<String, Object> data = new HashMap<>(); //返回数据

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(401);
        result.setMsg("失败");
        return result;
    }

    public Result add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
