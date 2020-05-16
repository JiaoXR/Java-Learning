package com.jaxer.doc.ioc;

import java.util.Random;

/**
 * Spring IoC测试
 */
public class Person {
    private int id;

    private String name;

    private Dog pet;

    public void init() {
        id = new Random().nextInt(100);
        name = "Jack-" + id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dog getPet() {
        return pet;
    }

    public void setPet(Dog pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
