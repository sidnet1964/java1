package ru.progwards.sid.test;

public class Person1 {
    public String name;
    private int age;
    private String country;
    public Person1(){
        country = "RU";
    }
    public Person1(String name, int age){
        this();
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getCountry(){
        return country;
    }
}
