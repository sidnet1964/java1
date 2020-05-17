package ru.progwards.java2.lessons.generics;
import java.util.Arrays;

//  H3 Домашнее задание
//  Задача 1. Свой алгоритм сортировки, класс ArraySort
//  Вместо создания массива мы можем воспользоваться обобщенным классом ArrayList
public class ArraySort {
    public static <T extends Comparable> void sort(T[] a){
//    public static void sort(int[] a){
        int n = a.length;                   //  размер полученного массива
        for (int i = 0; i < n; i++)         //  цикл по всем элементам массива
            for (int j = i+1; j < n; j++)   //  цикл по элементам справа от текущего
//                System.out.println((i)+"|"+(j));
                if (a[i].compareTo(a[j]) > 0){           //  сравнить элементы из разных циклов
                    T b = a[i];           //  переменная для обмена значениями
                    a[i] = a [j];
                    a[j] = b;
                }
    }
public static void main(String[] args) {
        String[] massiv = {"a", "4", "3", "2", "1"};
        sort(massiv);
        System.out.println(Arrays.toString(massiv));
    }
}
