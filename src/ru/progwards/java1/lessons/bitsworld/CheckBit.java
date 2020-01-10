package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {
    public static void main(String[] args) {
        System.out.println(checkBit((byte) 0b0101_0101, 6));
//        System.out.println(String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0'));
    }
    public static int checkBit(byte value, int bitNumber){
        final byte TEST = (byte) 0b0000_0001;
        byte result = 0;     // результат
//        for (byte i = 0; i < bitNumber; i++)
            value >>= bitNumber;
        result = (byte) (value & TEST);
        return result;
    }
}
