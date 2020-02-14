package ru.progwards.sid.N91;
import java.math.BigDecimal;

public class Rectangle {
    Rectangle(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }
    public BigDecimal a;
    public BigDecimal b;
    public BigDecimal area() {
        return a.multiply(b);
    }
    static boolean rectCompare(Rectangle r1, Rectangle r2){
        BigDecimal r1_area = r1.a.multiply(r1.b);
        BigDecimal r2_area = r2.a.multiply(r2.b);
        return r1_area.compareTo(r2_area) == 0;
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(new BigDecimal("2.5"), new BigDecimal("4.0"));
        Rectangle r2 = new Rectangle(new BigDecimal("2.0"), new BigDecimal("5.0"));
        System.out.println(rectCompare(r1, r2));
    }
}
