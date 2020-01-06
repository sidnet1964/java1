package ru.progwards.sid;

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

    public static void main(String[] args) {
        Rectangle r11 = new Rectangle(2,2);
        Rectangle r12 = new Rectangle(1,1);
        Rectangle r21 = new Rectangle(2,3);
        Rectangle r22 = new Rectangle(3,2);
        Rectangle r31 = new Rectangle(3,3);
        System.out.println(r11.compareTo(r12) );
    }
}
