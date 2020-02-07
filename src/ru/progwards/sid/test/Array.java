package ru.progwards.sid.test;

import java.util.Arrays;
import java.util.Comparator;

//import static java.util.Arrays.deepToString;
//import static java.util.Arrays.sort;

public class Array {
    public static void main(String[] args) {
//        int a1[], a2[], a3[];        int a[][] = {a1, a2, a3};
//        int a[10];
//        int a[] = new int[3];        int item = a[1][1];
//        int []a = new int(10);
//        int []a = new int[10];
        int[][] ark = {{7,5,3}, {8,6,4}, {6,3,1}};
//        Arrays.sort(ark, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return Integer.compare(o2[1], o1[1]);
//            }
//        });
//        int a[];
//        int a[] = {11, 22, 33};        int item = a[1];
//        int a[][];                     int item = a[1][2];
//        int a[] = (11, 22, 33);        int item = a[1];
//        int a[][][];
//        System.out.println(arrayMax(ar));
        System.out.println(Arrays.deepToString(ark));
        Arrays.sort(ark, Comparator.comparingInt(arr -> arr[1]));
        System.out.println(Arrays.deepToString(ark));
    }
    public static int sumArrayItems(int[] a){
        int su = 0;
        for ( int el : a)
            su += el;
        return su;
    }
    public static int arrayMax(int[] a){
        //  возвращает максимальный по значению элемент массива
        if (a.length == 0)
            return 0;
        else
            Arrays.sort(a);
            return a[a.length-1];
    }
}
