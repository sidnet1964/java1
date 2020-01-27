package ru.progwards.sid.test;

public class Person {
    // это вложенний класс
    static class Child1 {
        String hello() {
            return "привет";
        }
    }
    // это внутренний класс
    class Child2 {
        String hello() {
            return "servus";
        }
    }
//    class Dog implements Speaking, Eating {
//        String say() {
//            return "Гав";
//        }
//        String eat() {
//            return "Мясо";
//        }
//    }
//    class Goat implements Speaking, Eating {
//        String say() {
//            return "Мее";
//        }
//        String eat() {
//            return "Сено";
//        }
//    }
}
