package ru.progwards.java1.lessons.interfaces;
import java.util.Arrays;

public class ArraySort {
    public static void sort(CompareWeight[] a){
    //  public static void sort(int[] a){
        int n = a.length;
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                if (a[i].compareWeight(a[j]) == CompareWeight.CompareResult.GREATER){
                    CompareWeight b = a[i];
                    a[i] = a [j];
                    a[j] = b;
                }
    }
    public static void main(String[] args) {
        Cow burenka = new Cow(100);
        Duck donald = new Duck(10);
        Duck makdak = new Duck(10);
        Hamster tom = new Hamster(1);
        CompareWeight[] dvor = {tom, donald, makdak, burenka};
        //  int[] massiv = {4, 3, 2, 1};
        //  sort(massiv);
        sort(dvor);
        System.out.println(Arrays.toString(dvor));
    }
}
