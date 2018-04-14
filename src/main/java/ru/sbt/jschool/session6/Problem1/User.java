package main.java.ru.sbt.jschool.session6.Problem1;

import java.io.*;


public class User implements Serializable {

    private String name;
    private int age;
    private int salary;

    public User(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }


}
