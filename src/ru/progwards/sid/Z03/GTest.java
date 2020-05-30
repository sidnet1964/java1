package ru.progwards.sid.Z03;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.CharSequence.compare;

class GTest {
//    public  static <T extends Comparable> Comparable getItem(List<? extends Comparable> list, int index) {
//        return list.get(index);
//    }

//    public void addAll(List<T> list, T... items) {
//        for (T item : items) {
//            list.add(item);
//        }
//    }

//    public  static <T extends Throwable> T getItem(List<T> list, int index) {
//        try {
//            return list.get(index);
//        } catch (T e) {
//            return null;
//        }
//    }

//    public int myCompare(T a, T b) {
//        return a.compareTo(b);
//    }

//    public static T get(List<T> list, int index) {
//        return list.get(index);
//    }

//    public static <T extends Comparable<T>> GTest<T> from(List<T> list) {
//        GTest<T> result = new GTest<>();
//        //.. do something
//        return result;
//    }

    public static <T> void swap(List<T> list, int x, int y) {
        T z = list.get(x);           //  переменная для обмена значениями
        list.set(x, list.get(y));
        list.set(y, z);
//        System.out.println(list);
    }
//  Создайте статический метод с именем from, который принимает параметром массив, обобщающего типа, создает новый ArrayList, копирует в него содержимое массива и возвращает ArrayList в качестве результата метода.
//  public static <T extends Comparable> void sort(T[] a){
    public static <T> ArrayList<T> from(T[] a) {
//        ArrayList<T> arr = new ArrayList<>();
//        Collections.addAll(arr, a); // Добавляем массив
        ArrayList<T> arr = new ArrayList<T>(Arrays.asList(a));
        return arr;
    }

    public static <T extends Comparable> CompareResult compare(T x, T y) {
        int z = x.compareTo(y);
        if (z > 0) return CompareResult.GREATER;
        if (z < 0) return CompareResult.LESS;
        return CompareResult.EQUAL;
    }
    public enum CompareResult {LESS, EQUAL, GREATER};
    public static void main(String[] args) {
//        List<Integer> lst = List.of(1,2,3,4,5);
//        List<Integer> list = new ArrayList<>(lst);
//        swap(list, 1, 3);
        String[] massiv = {"a", "4", "3", "2", "1"};
        System.out.println(from(massiv));
//        String x = "aac";
//        String y = "aad";
//        System.out.println(compare(x,y));
    }

}
