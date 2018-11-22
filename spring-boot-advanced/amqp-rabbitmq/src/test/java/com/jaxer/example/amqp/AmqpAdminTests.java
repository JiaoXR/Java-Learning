package com.jaxer.example.amqp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * 测试AmqpAdmin
 * <p>
 * Created by jaxer on 2018/11/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AmqpAdminTests {
	@Autowired
	private AmqpAdmin amqpAdmin;

	@Test
	public void createExchange() {
		amqpAdmin.declareExchange(new DirectExchange("amqp.exchange"));
		log.info("Exchange创建成功");
	}

	@Test
	public void createQueue() {
		amqpAdmin.declareQueue(new Queue("amqp.queue"));
		log.info("Queue创建成功");
	}

	@Test
	public void createBinding() {
		amqpAdmin.declareBinding(new Binding("amqp.queue", Binding.DestinationType.QUEUE,
				"amqp.exchange", "amqp.hello", Collections.emptyMap()));
		log.info("Queue与Exchange绑定成功");
	}
}
