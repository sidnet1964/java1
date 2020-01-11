package ru.progwards.java1.lessons.figures;

public class Circle extends Figure {
    double radius;
    Circle (double radius) {    //  конструктор класса
        this.radius = radius;
    }
    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
    @Override
    double area () {
        return Math.PI * radius * radius;
    }
@Override
    public String toString(){
        return getClass().getName() + " # " + radius;
}
}
