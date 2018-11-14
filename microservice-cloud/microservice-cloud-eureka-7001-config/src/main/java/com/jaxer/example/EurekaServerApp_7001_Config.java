package com.jaxer.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by jaxer on 2018/11/5
 * Eureka配置版测试
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp_7001_Config {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp_7001_Config.class, args);
    }
}
