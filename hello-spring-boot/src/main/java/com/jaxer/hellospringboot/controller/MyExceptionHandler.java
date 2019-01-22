package com.jaxer.hellospringboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 出现特定异常时的处理方法
 * <p>
 * Created by jaxer on 2018/10/10
 */
@ControllerAdvice
public class MyExceptionHandler {
    //方式一
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleException(RuntimeException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 404);
        map.put("exception", e);
        return map;
    }

    //方式二（自适应，根据浏览器和客户端分别返回页面和JSON数据）
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleException2(RuntimeException e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("javax.servlet.error.status_code", request.getAttribute("")); //设置错误码
        map.put("code", 404);
        map.put("msg", e.getMessage());
        request.setAttribute("errorMsg", map);
        return map;
    }
}
