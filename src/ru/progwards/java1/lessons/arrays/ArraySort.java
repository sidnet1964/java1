package ru.progwards.java1.lessons.arrays;
import java.util.Arrays;

public class ArraySort {
    public static void sort(int[] a){
        int n = a.length;
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                if (a[i] > a[j]){
                    int b = a[i];
                    a[i] = a [j];
                    a[j] = b;
                }
    }
    public static void main(String[] args) {
        int[] massiv = {4, 3, 2, 1};
        sort(massiv);
        System.out.println(Arrays.toString(massiv));
    }
}
