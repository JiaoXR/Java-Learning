package com.jaxer.doc.ioc;

public class Dog {
    private int age;

    private Person owner;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                '}';
    }
}
