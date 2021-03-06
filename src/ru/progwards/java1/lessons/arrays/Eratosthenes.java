package ru.progwards.java1.lessons.arrays;
import java.util.Arrays;

//  Задача 1. Решето Эратосфена, класс Eratosthenes
public class Eratosthenes {
    private boolean[] sieve;    // массив чисел, собственно, "решето"
    public Eratosthenes(int N){ // конструктор
        sieve = new boolean[N+1];   // на 1 больше
        Arrays.fill(sieve, true);
        sift(N+1);
    }
    private void sift(int N){
        for (int i = 2; i < N; i++)
            if (sieve[i])
                for (int j = i+i; j < N; j += i)
                    sieve[j] = false;
    }
    public boolean isSimple(int n)
    {
        if (n >= sieve.length){
            System.out.println("n = " + n + ", это больше длины массива");  return false;}
        else
            return sieve[n];
    }
    public static void main(String[] args) {
        int i = 40;
        Eratosthenes sito = new Eratosthenes(40);
        System.out.println(sito.isSimple(i));
        System.out.println(Arrays.toString(sito.sieve));
    }
}
