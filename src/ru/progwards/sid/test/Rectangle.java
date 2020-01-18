package ru.progwards.sid.test;

import java.util.Objects;

public class Rectangle {
    private double a;
    private double b;
    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }
    public double area() {
        return a*b;
    }
    public int compareTo(Rectangle anRectangle){
        if (this.area() > anRectangle.area())
            return 1;
        if (this.area() < anRectangle.area())
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return area() == rectangle.area();
//                Double.compare(rectangle.a, a) == 0 &&
//                Double.compare(rectangle.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    public static void main(String[] args) {
//        Rectangle r11 = new Rectangle(2,2);
//        Rectangle r12 = new Rectangle(1,1);
        Rectangle r21 = new Rectangle(2,3);
        Rectangle r22 = new Rectangle(3,2);
        Rectangle r31 = new Rectangle(3,3);
//        System.out.println(r11.compareTo(r12) );
        System.out.println(r31.equals(r22));
    }
}
