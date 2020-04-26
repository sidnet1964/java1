package ru.progwards.java1.lessons.basics;

//  H3 Домашнее задание
//  Задача 3. Класс AccuracyDoubleFloat
public class AccuracyDoubleFloat {
    public static void main(String[] args) {
        System.out.println(calculateAccuracy(6371.2d));
    }
    //  возвращает объём шара с радиусом radius и основана на типе double
    public static double volumeBallDouble(double radius){
        final double PI = 3.14d;
        final double KF = 1.333333333d;
        Double volume = KF * PI * radius * radius * radius;
        return volume;
    }
    //  возвращает объём шара с радиусом radius и основана на типе float
    public static float volumeBallFloat(float radius){
        final float PI = 3.14f;
        final float KF = 1.333333333f;
        Float volume = KF * PI * radius * radius * radius;
        return volume;
    }
    //  возвращает разницу между функциями volumeBallDouble и volumeBallFloat
    public static double calculateAccuracy(double radius){
        Double calculate = volumeBallDouble(radius) - volumeBallFloat((float) radius);
        return calculate;
    }
}
