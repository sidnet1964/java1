package ru.progwards.java1.lessons.basics;

//  Задача 2. Класс Astronomy
public class Astronomy {
    public static void main(String[] args) {
        System.out.println(earthVsMercury());
        System.out.println(earthVsJupiter());
    }
    //  вычисляет площадь поверхности сферы радиуса R по формуле S = 4πR2
    public static Double sphereSquare(Double r){
        final float PI = 3.14f;
        Double square = 4 * PI * r * r;
        return square;
    }
    //  вычисляет площадь поверхности Земли, считая радиус равным
    //  6 371.2 км и используя функцию sphereSquare
    public static Double earthSquare(){
        final double R_EARTH = 6371.2d;
        return sphereSquare(R_EARTH);
    }
    public static Double mercurySquare(){
        final double R_MERCURY = 2439.7d;
        return sphereSquare(R_MERCURY);
    }
    public static Double jupiterSquare(){
        final double R_JUPITER = 71492.0d;
        return sphereSquare(R_JUPITER);
    }
    public static Double earthVsMercury(){
        return earthSquare() / mercurySquare();
    }
    public static Double earthVsJupiter(){
        return earthSquare() / jupiterSquare();
    }
}
