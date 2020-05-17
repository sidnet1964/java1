package ru.progwards.java2.lessons.generics;

//  H3 Домашнее задание Задача 3. Класс FruitBox
//  Реализовать классы Fruit и потомки Apple, Orange
public class Fruit {
    float weight;
    boolean tail;   // с хвостиком или без

    //  конструктор
    public Fruit(boolean tail) {
        this.tail = tail;
    }

    public float getWeight(){
        return weight;
    }
}
