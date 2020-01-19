package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {
    private static CacheInfo lastFibo;
    //  2.3 Разместить в классе CalculateFibonacci приватную статическую переменную CacheInfo lastFibo
    public static int fiboNumber(int n){
        //  2.1 Разместить в классе CalculateFibonacci функцию fiboNumber
        //  сделать сохранение одного, последнего из рассчитанных значений
        //  2.4 В статической функции fiboNumber, проверять параметр n
        if (n == lastFibo.n)
            return lastFibo.fibo;
        //System.out.println("b - " + n);
        //  F[0] = 0, F[1] = 1, ..., F[n] = F[n-1] + F[n-2]
        int f_n_2 = 0;  //  F[n-2]
        int f_n_1 = 1;  //  F[n-1]
        int f_n = 1;    //  F[n]
        for (int i = 2; i <= n; i++)
        {f_n = f_n_2 + f_n_1;
            f_n_2 = f_n_1;
            f_n_1 = f_n;}
        lastFibo.n = n;
        lastFibo.fibo = f_n;
        return f_n;
    }
    public static class CacheInfo {
        //  2.2 Разместить вложенный класс CacheInfo
        public int n;       // - число, для которого рассчитываем Фибоначчи
        public int fibo;    // - результат расчета
        CacheInfo(int n, int fibo) { //  конструктор
            this.n = n;
            this.fibo = fibo;
        }
        public static CacheInfo getLastFibo(){
            return lastFibo;
        }
        public static void clearLastFibo(){
            lastFibo = null;
        }
    }
    public static void main(String[] args) {
        lastFibo = new CacheInfo(1,1);
        for (int i=1; i<=10; i++) {
//            System.out.println(fiboNumber(i) + " == " + lastFibo.n + " -- " + lastFibo.fibo);
//            System.out.println(fiboNumber(i) + " == " + lastFibo.n + " -- " + lastFibo.fibo);
            System.out.println(fiboNumber(i));
            System.out.println(fiboNumber(i));
        }
        System.out.println("---");
    }
}
