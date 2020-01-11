package ru.progwards.java1.lessons.figures_book;

public class Figure {
    double dim1;
    double dim2;
    Figure(double a, double b) {
        dim1 = a;
        dim2 = b;
    }
    double area() {
        System.out.println("Площадь фигуры не определена.");
        return 0;
    }
}
class Rectangle extends Figure {
    Rectangle (double a, double b) {
        super(a, b);
    }
    double area () {    //переопределить метод area() для четырехугольника
        System.out.println("B области четырехугольника.");
        return dim1 * dim2;
    }
}
class Triangle extends Figure {
    Triangle(double a, double b) {
        super(a, b);
    }
    double area () {    // переопределить метод area() для прямоугольного треугольника
        System.out.println("B области треугольника.");
        return dim1 * dim2 / 2;
    }
}
