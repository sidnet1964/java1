package ru.progwards.sid;

public class Array {
    public static void main(String[] args) {
//        int a1[], a2[], a3[];        int a[][] = {a1, a2, a3};
//        int a[10];
//        int a[] = new int[3];        int item = a[1][1];
//        int []a = new int(10);
//        int []a = new int[10];
        int[] ar = {10, 26, 12, 35, -1};
//        int a[];
//        int a[] = {11, 22, 33};        int item = a[1];
//        int a[][];                     int item = a[1][2];
//        int a[] = (11, 22, 33);        int item = a[1];
//        int a[][][];
        System.out.println(arrayMax(ar));
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
            java.util.Arrays.sort(a);
            return a[a.length-1];
    }
}
