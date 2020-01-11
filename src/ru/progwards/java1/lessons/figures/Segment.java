package ru.progwards.java1.lessons.figures;

public class Segment extends Figure {
    double a;
    Segment (double a) {    //  конструктор класса
        this.a = a;
    }
    @Override
    double perimeter() {
        return a;
    }
    @Override
    public String toString(){
        return getClass().getName() + " # " + a;
    }

}
