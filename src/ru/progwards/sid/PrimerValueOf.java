package ru.progwards.sid;

public class PrimerValueOf {
    public static void main(String[] args) {
        //Integer int1 = new Integer(5);    // избыточная конструкция для оберток
        Integer int1 = 5;                   // упрощенная конструкция - автоупаковка
        Byte byte1 = 5;
        Short short1 = 5;
        Double double1 = 5.0;
        Boolean boolean1 = true;
        // конструктор вызывается неявно
        System.out.println(int1);
        System.out.println(byte1);
        System.out.println(short1);
        System.out.println(double1);
        System.out.println(boolean1);
        // Метод valueOf() в Java является статическим методом, действует для всех классов-обуёрток.
        Integer int11 = Integer.valueOf(5);
        Integer int12 = Integer.valueOf("555");
        Double double11 = Double.valueOf(5);
        Double double12 = Double.valueOf(5.0);
        Double double13 = Double.valueOf("555");
        System.out.println(int11);
        System.out.println(int12);
        System.out.println(double11);
        System.out.println(double12);
        System.out.println(double13);
    }}
