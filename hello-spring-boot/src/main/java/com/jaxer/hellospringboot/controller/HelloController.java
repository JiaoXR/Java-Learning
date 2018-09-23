package com.jaxer.hellospringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jaxer on 22/09/2018
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello, SpringBoot!";
    }

}
