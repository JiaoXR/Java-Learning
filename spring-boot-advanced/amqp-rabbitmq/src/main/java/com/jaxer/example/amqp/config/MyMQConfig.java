package com.jaxer.example.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jaxer on 2018/11/22
 */
@Configuration
public class MyMQConfig {
    /**
     * 配置序列化方式（默认为JDK序列化）
     *
     * @see org.springframework.amqp.rabbit.core.RabbitTemplate
     * @see org.springframework.amqp.support.converter.MessageConverter
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
