package org.reform.model;


import org.reform.annotation.Entity;

import java.util.ArrayList;

//@Entity
public class Person {
    private String name;
    private int age;
    private Pet pet;
//    private ArrayList<Pet> pets;

    public Person(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
