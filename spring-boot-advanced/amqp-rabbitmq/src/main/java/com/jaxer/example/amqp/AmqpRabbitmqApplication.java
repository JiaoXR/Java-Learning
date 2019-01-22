package com.jaxer.example.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class AmqpRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpRabbitmqApplication.class, args);
	}
}
