package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Creator {
    public static Collection<Integer> fillEven(int n){
        Collection<Integer> list1 = new ArrayList<>();
        for (int i = 1; i < n+1; i++)
            list1.add(i+i);
        return list1;
    }
    public static Collection<Integer> fillOdd(int n){
        Collection<Integer> list0 = new LinkedList<>();
        LinkedList<Integer> list1 = new LinkedList<>();
        int j = 0;
        for (int i = 1; ; i+=2) {
            list1.add(0, i);
            j++;
            if (j == n * 3)
                break;
        }
        list0.addAll(list1);
        return list0;
    }
    public static Collection<Integer> fill3(int n){
        Collection<Integer> list0 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list0.add(i);
            list0.add(i * i);
            list0.add(i * i * i);
        }
        return list0;
    }
    public static void main(String[] args) {
//        System.out.println(fillEven(10));
//        System.out.println(fillOdd(3));
        System.out.println(fill3(3));
    }
}
