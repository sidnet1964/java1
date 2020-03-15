package ru.progwards.sid.N14;

import java.util.ArrayDeque;

public class LastAndFirst {
    static int sumLastAndFirst(ArrayDeque<Integer> deque){
        int sum1 = 0;
        int sum2 = 0;
        if (deque.peekFirst() != null)
            sum1 = deque.peekFirst();
        if (deque.peekLast() != null)
            sum2 = deque.peekLast();
        return sum1 + sum2;
    }

    public static void main(String[] args) {
        final int ITERATIONS = 11;
        ArrayDeque<Integer> arrayDeque1 = new ArrayDeque<>();
        for (int i = 10; i < ITERATIONS; i++) arrayDeque1.offer(i);
        System.out.println(arrayDeque1);
        System.out.println(sumLastAndFirst(arrayDeque1));
    }
}
