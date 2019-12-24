package ru.progwards.java1.lessons.basics;

public class ReverseDigits {
    public static void main(String[] args) {
        System.out.println(reverseDigits(987));
    }
    public static int reverseDigits(int number){
        int x = number % 10;
        int y = number / 10 % 10;
        int z = number / 100 % 10;
        return x * 100 + y * 10 +z;
    }
}
