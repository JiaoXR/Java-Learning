package com.jaxer.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by jaxer on 2018/11/14
 */
@SpringBootApplication
@EnableConfigServer
public class CloudConfigApp {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApp.class, args);
    }
}
