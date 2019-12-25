package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {
    public static void main(String[] args) {
        System.out.println(isEquilateralTriangle(2,3,2));
    }
    public static int maxSide(int a, int b, int c){
        int ms = a;
        if (b > ms)
            ms = b;
        if (c > ms)
            ms = c;
        return ms;
    }
    public static int minSide(int a, int b, int c){
        int ms = a;
        if (b < ms)
            ms = b;
        if (c < ms)
            ms = c;
        return ms;
    }
    public static boolean isEquilateralTriangle(int a, int b, int c){
        return a == b && a == c && b == c;
    }
}
