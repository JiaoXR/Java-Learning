package com.jaxer.hellospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 可以使用配置文件（beans.xml）方式配置，但不推荐
 * 推荐使用配置类的方式
 *
 * @see com.jaxer.hellospringboot.config.MyConfig
 */
//@ImportResource("classpath:beans.xml")
@SpringBootApplication
public class HelloSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootApplication.class, args);
    }
}
