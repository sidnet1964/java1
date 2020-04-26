package ru.progwards.java1.lessons.compare_if_cycles;

//  Задача 2. Класс TriangleInfo
public class TriangleInfo {
    public static void main(String[] args) {
        System.out.println(isIsoscelesTriangle(5, 4, 5));
    }
    //  возвращает true, если по данным трём сторонам (a, b, c) можно построить треугольник
    public static boolean isTriangle(int a, int b, int c){
        int d;
        if (a > b)         {d = a; a = b; b = d;}
        if (a > c)         {d = a; a = c; c = d;}
        if (b > c)         {d = b; b = c; c = d;}
        return a + b > c;
    }
    //  возвращает true, если треугольник со сторонами a, b, c является прямоугольным
    public static boolean isRightTriangle(int a, int b, int c){
        int d;
        if (a > b)         {d = a; a = b; b = d;}
        if (a > c)         {d = a; a = c; c = d;}
        if (b > c)         {d = b; b = c; c = d;}
        return a*a + b*b == c*c;
    }
    //  возвращает true, если треугольник со сторонами a, b, c является равнобедренным
    public static boolean isIsoscelesTriangle(int a, int b, int c){
        return a == b || a == c || b == c;
    }
}
