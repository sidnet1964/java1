package ru.progwards.java1.lessons.arrays;
import java.util.Arrays;

//  H6 Домашнее задание
//  Задача 2. Свой алгоритм сортировки, класс ArraySort
public class ArraySort {
    public static void sort(int[] a){
        int n = a.length;                   //  размер полученного массива
        for (int i = 0; i < n; i++)         //  цикл по всем элементам массива
            for (int j = i+1; j < n; j++)   //  цикл по элементам справа от текущего
                System.out.println((i)+"|"+(j));
                //                if (a[i] > a[j]){           //  сравнить элементы из разных циклов
//                    int b = a[i];           //  переменная для обмена значениями
//                    a[i] = a [j];
//                    a[j] = b;
//                }
    }
public static void main(String[] args) {
        int[] massiv = {5, 4, 3, 2, 1};
        sort(massiv);
        System.out.println(Arrays.toString(massiv));
    }
}
