package ru.progwards.java1.lessons.figures;

public class Triangle extends Segment {
    double b;
    double c;

    Triangle(double a, double b, double c){
        super(a);
        this.b = b;
        this.c = c;
    }
    @Override
    double perimeter() {
        return a + b +c;
    }
    @Override
    double area () {
        double hP = perimeter() /2;
        return Math.sqrt(hP * (hP - a) * (hP - b) *(hP - c));
    }
    @Override
    public String toString(){
        return getClass().getName() + " # " + a + " # " + b + " # " + c;
    }
}
