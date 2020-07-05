package ru.progwards.java2.lessons.threads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Summator {
    int count;
    BigInteger[] rezult;    //  массив для итогов по потокам

    public Summator(int count) {
        this.count = count;
        rezult = new BigInteger[count];    //  массив для итогов по потокам
    }
    //  --------------------------------
    //  класс для создания потока
    class MyCounter implements Runnable {
        private final int section;
        private final BigInteger beg; //  начало интервала
        private final BigInteger end; //  конец интервала
        private BigInteger rez;

        public MyCounter(int section, BigInteger beg, BigInteger end) {
            this.section = section;
//            rezult[section] = BigInteger.ZERO;
            this.beg = beg;
            this.end = end;
            rez = BigInteger.ZERO;
        }

        @Override
        public void run() {
            for (BigInteger i = beg; i.compareTo(end) <= 0;) {
//                System.out.println(this);
//                rezult[section] = rezult[section].add(i);
                rez = rez.add(i);
                //  счетчик цикла вынесен отдельно
                i = i.add(BigInteger.ONE);
            }
            rezult[section] = rez;
//            System.out.println("section - " + section);
//            System.out.println("section - " + section + " currentThread() " + Thread.currentThread().getState());
        }
    }
    //  --------------------------------
    public BigInteger sum(BigInteger number) throws InterruptedException {
//        long n = number.longValue() / count;  //  количество итераций в одном потоке
        BigInteger nn = number.divide(BigInteger.valueOf(count));   //возвращает частное двух чисел
        BigInteger mm = number.mod(BigInteger.valueOf(count));      //остаток от деления двух чисел
        List<Thread> threads = new ArrayList<>();

//        System.out.println("nn = " + nn + " mm = " + mm);
        for (int l = 0; l<count; l++) {
            if (l == count-1)
                //  конец интервала - number
                threads.add(l, new Thread(new MyCounter(l, nn.multiply(BigInteger.valueOf(l)).add(BigInteger.ONE), number)));
            else
                threads.add(l, new Thread(new MyCounter(l, nn.multiply(BigInteger.valueOf(l)).add(BigInteger.ONE), nn.multiply(BigInteger.valueOf(l+1)))));
            threads.get(l).start();
            threads.get(l).join();
//            new Thread(new MyCounter(l, nn.multiply(BigInteger.valueOf(l)).add(BigInteger.ONE), nn.multiply(BigInteger.valueOf(l+1)))).start();
        }
        Thread.sleep(500);
//        System.out.println("=================");
//        for (Thread one : threads)
//            System.out.println("section - " + one + " currentThread() " + Thread.currentThread().getState());

        BigInteger itog = BigInteger.ZERO;
        for (int l = 0; l<count; l++) {
            System.out.println("l = " + l + " -> " + rezult[l]);
            itog = itog.add(rezult[l]);
        }
//        System.out.println("#################");
//        for (Thread one : threads)
//            System.out.println("section - " + one + " currentThread() " + Thread.currentThread().getState());
        return itog;
    }

    public static void main(String[] args) throws InterruptedException {
        Summator summator = new Summator(64);
        BigInteger itog = BigInteger.ZERO;
        BigInteger pred = BigInteger.valueOf((long)Integer.MAX_VALUE * 2);
        long start = System.currentTimeMillis();

//        System.out.println(summator.sum(BigInteger.valueOf(Integer.MAX_VALUE).multiply(BigInteger.valueOf(Integer.MAX_VALUE))));
        itog = summator.sum(pred);
//        System.out.println(summator.sum(BigInteger.TEN));

//        for (BigInteger i = BigInteger.ONE; i.compareTo(pred) <= 0;) {
////                System.out.println(this);
//            itog = itog.add(i);
//            //  счетчик цикла вынесен отдельно
//            i = i.add(BigInteger.ONE);
//        }
        System.out.println(pred + ";" + itog + ";" + (System.currentTimeMillis() - start));
//        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
//        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
    }
}
