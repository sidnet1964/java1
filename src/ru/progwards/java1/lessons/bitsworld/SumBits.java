package ru.progwards.java1.lessons.bitsworld;

//  Задача 1. Класс SumBits
public class SumBits {
    public static void main(String[] args) {
        System.out.println(sumBits((byte) 0b1100_0011));
    }
    //  суммирует все биты параметра value
    public static int sumBits(byte value){
        final int RAZ = 8;  // количество разрядов в byte
        final byte TEST = (byte) 0b0000_0001;
        byte result = 0;     // результат
        for (byte i = 1; i <= RAZ; i++){
            result += value & TEST;
            value >>= 1;
        }
        return result;
    }
}
