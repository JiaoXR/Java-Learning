package com.jaxer.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by jaxer on 2018/11/2
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.jaxer.example"})
@ComponentScan("com.jaxer.example")
public class DeptConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerApp.class, args);
    }
}
