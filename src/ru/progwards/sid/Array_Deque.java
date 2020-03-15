package ru.progwards.sid;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Array_Deque {
    static ArrayDeque<Integer> array2queue(int[] a){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        try {
        for (int x : a) {
            deque.offer(x);
        }
        } catch (NullPointerException e) {
            System.out.println(e.fillInStackTrace());
        }        return deque;
    }
    public static void main(String[] args) {
        ArrayDeque deque = new ArrayDeque<>();

        for (int i = 0; i <= 10; i++) {
            deque.offer(i);
            if (i%2 == 0)
                deque.poll();
        }
        System.out.println(deque);
        int[] ar = {};
        System.out.println(array2queue(null));
//        PriorityQueue pQueue = new PriorityQueue<>();
//        pQueue.offer(10);
//        pQueue.offer(1);
//        pQueue.offer(9);
//        pQueue.offer(3);
//        System.out.println(pQueue);

    }

}
