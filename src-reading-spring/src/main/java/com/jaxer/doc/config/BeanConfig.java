package com.jaxer.doc.config;

import com.jaxer.doc.ioc.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 */
@Configuration
public class BeanConfig {
    @Bean
    public Person person() {
        Person person = new Person();
        person.setId(100);
        person.setName("Ace");
        return person;
    }
}
