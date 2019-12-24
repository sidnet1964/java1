package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {
    public static void main(String[] args) {
        System.out.println(calculateAccuracy(6371.2));
    }
    public static double volumeBallDouble(double radius){
        final float PI = 3.14f;
        final double KF = 1.333333333d;
        Double volume = KF * PI * radius * radius * radius;
        return volume;
    }
    public static float volumeBallFloat(float radius){
        final float PI = 3.14f;
        final float KF = 1.333333333f;
        Float volume = KF * PI * radius * radius * radius;
        return volume;
    }
    public static double calculateAccuracy(double radius){
        Double calculate = volumeBallDouble(radius) / volumeBallFloat((float) radius);
        return calculate;
    }
}
