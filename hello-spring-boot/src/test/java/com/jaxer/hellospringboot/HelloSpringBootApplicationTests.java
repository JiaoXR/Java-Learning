package com.jaxer.hellospringboot;

import com.jaxer.hellospringboot.bean.Cat;
import com.jaxer.hellospringboot.bean.Dog;
import com.jaxer.hellospringboot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloSpringBootApplicationTests {

    @Autowired
    private Person person;
    @Autowired
    private Dog dog;
    @Autowired
    private Cat cat;
    @Autowired
    private ApplicationContext context;

    @Test
    public void testImport() {
        System.out.println(context.getBean("helloService"));
    }

    @Test
    public void contextLoads() {
        System.out.println(person);
        System.out.println(dog);
        System.out.println(cat);
    }

}
