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
        Food apple = new Food(1);
        Food bear = new Food(2);
        Food orange = new Food(3);
        CompareWeight[] tarelka = {apple, orange, bear};
        //  int[] massiv = {4, 3, 2, 1};
        //  sort(massiv);
        sort(tarelka);
        System.out.println(Arrays.toString(tarelka));
    }
}
