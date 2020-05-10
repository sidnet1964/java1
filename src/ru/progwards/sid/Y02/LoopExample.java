package ru.progwards.sid.Y02;

import java.util.List;

public class LoopExample {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        for (Integer element : list)
            System.out.println("element = " + element);
        System.out.println();
        list.forEach(element -> System.out.println("element = " + element));
        System.out.println();
        //  создать ссылку на функциональный интерфейс MyNumЬer
        MyNumЬer myNum;
        // использовать лямбда-выражение в контексте присваивания
        myNum = () -> 123.45;
        // вызвать метод getValue(), реализуемый
        // присвоенным ранее лямбда-выражением
        System. out .println ( myNum.getValue ());
//А здесь используется более сложное выражение
        myNum = () -> Math.random() * 100;
//В следующих строках кода вызывается лямбда-выражение
//из предыдущей строки кода
        System.out.println("Cлyчaйнoe значение: " + myNum.getValue());
        System.out.println("Eщe одно случайное значение: " + myNum.getValue());
    }
}
