package ru.progwards.java1.lessons.figures;

public class Square extends Segment{
    Square(double a){
        super(a);
    }
    @Override
    double perimeter() {
        return 4 * a;
    }
    @Override
    double area () {
        return a * a;
    }
    @Override
    public String toString(){
        return getClass().getName() + " # " + a +" # "+ a;
    }
}
