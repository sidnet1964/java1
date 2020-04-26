package ru.progwards.java1.lessons.basics;

//  Задача 1. Класс ReverseDigits
public class ReverseDigits {
    public static void main(String[] args) {
        System.out.println(reverseDigits(987));
    }
    //  получает параметром number трёхзначное положительное число,
    //  а вернуть должна число в котором цифры идут в обратном порядке
    //  (например, если на вход передаётся 123, то функция должна вернуть 321)
    public static int reverseDigits(int number){
        int x = number % 10;
        int y = number / 10 % 10;
        int z = number / 100 % 10;
        return x * 100 + y * 10 +z;
    }
}
