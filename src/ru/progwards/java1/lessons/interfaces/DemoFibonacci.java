package ru.progwards.java1.lessons.interfaces;

import static ru.progwards.java1.lessons.interfaces.CalculateFibonacci.fiboNumber;

public class DemoFibonacci {
    public static void main(String[] args) {
//        CalculateFibonacci.CacheInfo lastFibo = new CalculateFibonacci.CacheInfo(1,1);
//        System.out.println(lastFibo.n + " -- " + lastFibo.fibo);
        for (int i=1; i<=4; i++) {
            System.out.println(CalculateFibonacci.fiboNumber(i));
        }
        System.out.println("---");
    }
}
