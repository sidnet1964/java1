package ru.progwards.java1.lessons.bitsworld;

//  Задача 2. Класс CheckBit
public class CheckBit {
    public static void main(String[] args) {
        System.out.println(checkBit((byte) 0b0101_0101, 6));
    }
    //  возвращает значение бита с порядковым номером bitNumber от параметра value
    public static int checkBit(byte value, int bitNumber){
        final byte TEST = (byte) 0b0000_0001;
        byte result = 0;     // результат
//        for (byte i = 0; i < bitNumber; i++)
            value >>= bitNumber;
        result = (byte) (value & TEST);
        return result;
    }
}
