package com.jaxer.example.amqp;

import com.jaxer.example.amqp.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AmqpRabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMsg() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "刘备");
//        map.put("gender", 1);
//        map.put("age", 50);
//        rabbitTemplate.convertAndSend("exchange.direct", "jaxer.emps", map);

        rabbitTemplate.convertAndSend("exchange.direct", "jaxer.emps", new Person("关羽", 1, 48));
    }

    @Test
    public void receiveMsg() {
        Object msg = rabbitTemplate.receiveAndConvert("jaxer.emps");
        log.info("class-->" + msg.getClass());
        log.info("msg-->" + msg);
    }

}
