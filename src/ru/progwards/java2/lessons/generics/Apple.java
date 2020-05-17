package ru.progwards.java2.lessons.generics;

//  Реализовать классы Fruit и потомки Apple, Orange
public class Apple extends Fruit {
    public Apple(boolean tail){ // конструктор класса
        super(tail);
        this.weight = getCoeff();

    }
    public float getCoeff(){
        return 1.0f;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", tail=" + tail +
                '}';
    }
}
