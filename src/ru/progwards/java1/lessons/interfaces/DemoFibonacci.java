package ru.progwards.java1.lessons.interfaces;

import static ru.progwards.java1.lessons.interfaces.CalculateFibonacci.fiboNumber;

public class DemoFibonacci {
    public static void main(String[] args) {
        CalculateFibonacci.CacheInfo lastFibo = new CalculateFibonacci.CacheInfo(1,1);
        for (int i=1; i<=10; i++) {
//            System.out.println(fiboNumber(i) + " == " + lastFibo.n + " -- " + lastFibo.fibo);
//            System.out.println(fiboNumber(i) + " == " + lastFibo.n + " -- " + lastFibo.fibo);
            System.out.println(fiboNumber(i));
            System.out.println(fiboNumber(i));
        }
        System.out.println("---");
    }

}
