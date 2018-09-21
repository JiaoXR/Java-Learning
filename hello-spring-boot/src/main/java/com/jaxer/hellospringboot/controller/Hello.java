package com.jaxer.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jaxer on 22/09/2018
 */
@Controller
public class Hello {

    @RequestMapping("/hello")
    @ResponseBody
    public String helloWorld() {
        return "Hello, SpringBoot!";
    }

}
