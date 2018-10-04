package com.jaxer.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by jaxer on 22/09/2018
 */
//@RestController
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String helloWorld() {
        return "Hello, SpringBoot!";
    }

    /**
     * 使用 Thymeleaf 模板
     *
     * @return 模板文件
     */
    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "success~");
        return "success";
    }
}
