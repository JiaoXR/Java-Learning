package com.jaxer.example.amqp.service;

import com.jaxer.example.amqp.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by jaxer on 2018/11/22
 * 监听消息队列
 *
 * @see org.springframework.amqp.rabbit.annotation.RabbitListener
 * @see org.springframework.amqp.rabbit.annotation.EnableRabbit
 */
@Service
@Slf4j
public class PersonService {
	@RabbitListener(queues = "jaxer.emps")
	public void receive1(Person person) {
		log.info("收到消息：" + person);
	}

//	@RabbitListener(queues = "jaxer.emps")
//	public void receive2(Message message) {
//		log.info("消息头：" + message.getMessageProperties());
//		log.info("消息体：" + Arrays.toString(message.getBody()));
//	}
}
