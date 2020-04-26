package ru.progwards.java1.lessons.bitsworld;

//  H7 Домашнее задание
//  Задача 3. Класс Binary
public class Binary {
    public byte num;
    public Binary(byte num){
        this.num = num;
    }
//  возвращает двоичное представление числа типа byte, используя только битовые операции
    public String toString(){
        final int TEST = 0b00000000_00000000_00000000_11111111;
        return String.format("%8s", Integer.toBinaryString((int)num & TEST)).replace(' ', '0');
    }
    public static void main(String[] args) {
        Binary number = new Binary((byte)(125));
        System.out.println(number);
    }
}
