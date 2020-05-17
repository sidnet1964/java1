package ru.progwards.java2.lessons.generics;

public class Orange extends Fruit {
    public Orange(boolean tail){ // конструктор класса
        super(tail);
        this.weight = getCoeff();
    }
    public float getCoeff(){
        return 1.5f;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "weight=" + weight +
                ", tail=" + tail +
                '}';
    }
}
