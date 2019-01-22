package com.jaxer.example;

import com.jaxer.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Created by jaxer on 2018/11/2
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "MICROSERVICE-CLOUD-DEPT", configuration = MyRule.class)
public class DeptConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerApp.class, args);
    }
}
