package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    private boolean[] sieve;    // массив чисел, собственно, "решето"
    public Eratosthenes(int N){ // конструктор
        sieve = new boolean[N+1];   // на 1 больше
        Arrays.fill(sieve, true);
        sift(N+1);
    }
    private void sift(int N){
        for (int i = 2; i < N; i++)
            for (int j = i+i; j < N; j += i)
                sieve[j] = false;
    }
    public boolean isSimple(int n)
    {
        return sieve[n];    // как быть если n > sieve.length ?
    }
    public static void main(String[] args) {
        int i = 3;
        Eratosthenes Sito = new Eratosthenes(2);
        System.out.println(Sito.isSimple(i));
    }
}
