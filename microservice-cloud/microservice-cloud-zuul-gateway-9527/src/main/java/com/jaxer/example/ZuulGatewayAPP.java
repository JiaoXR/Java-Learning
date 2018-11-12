package com.jaxer.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by jaxer on 2018/11/12
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulGatewayAPP {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayAPP.class, args);
    }
}
