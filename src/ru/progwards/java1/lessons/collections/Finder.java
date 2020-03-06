package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Finder {
//  найти 2 соседних числа в коллекции сумма которых минимальна,
//  вернуть коллекцию, содержащую индексы этих чисел
public static Collection<Integer> findMinSumPair(Collection<Integer> numbers){
    List<Integer> list1 = new ArrayList();  //  для результата
    if (numbers.isEmpty())
        return list1;
    if (numbers.size() < 2)
        return list1;
    Integer[] obj = numbers.toArray(new Integer[0]);    //  забавная конструкция, сам бы не придумал
    int sum_min = 0;
    int ind_min = 0;
    for (int i = 0; i < obj.length-1; i++) {  //  цикл по индексам массива
        int sum = obj[i] + obj[i+1];
        if (sum < sum_min){
            sum_min = sum;
            ind_min = i;
        }
    }
//    System.out.println(ind_min + "|" + sum_min);
    list1.add(ind_min);
    list1.add(ind_min+1);
    return list1;
    }
//  найти локальные максимумы - числа, которые больше соседа справа и слева.
//  Первый и последний элемент коллекции не может являться локальным  максимумом,
//  вернуть коллекцию, содержащую значения этих максимумов
    public static Collection<Integer> findLocalMax(Collection<Integer> numbers){
        List<Integer> list1 = new ArrayList();  //  для результата
        if (numbers.size() < 3)
            return list1;
        Integer[] obj = numbers.toArray(new Integer[0]);    //  забавная конструкция, сам бы не придумал
        for (int i = 1; i < obj.length-1; i++) {  //  цикл по индексам массива
            if (obj[i] > obj[i-1] && obj[i] > obj[i+1])
                list1.add(i);
            }
        return list1;
    }
//   - проверить, содержит ли коллекция все числа от 1 до size(), порядок может быть произвольный
    public static boolean findSequence(Collection<Integer> numbers){
        if (numbers.isEmpty())
            return false;
        Integer[] obj = numbers.toArray(new Integer[0]);    //  забавная конструкция, сам бы не придумал
        Arrays.sort(obj);
//        System.out.println(Arrays.toString(obj));
        for (int i = 0; i < obj.length; i++) {  //  цикл по индексам массива
            if (obj[i] != i+1)
                return false;
        }
    return true;
    }
    //  подготовка коллекции для работы
    public static Collection<Integer> fillFull(int n){
        Collection<Integer> list1 = new ArrayList<>();
        for (int i = 1; i < n+1; i++)
            list1.add(11 - i % 10); //
        return list1;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
//        Collection<Integer> list1 = fillFull(10);
        System.out.println(list1);
//        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(findMinSumPair(list1));
        System.out.println(findLocalMax(list1));
        System.out.println(findSequence(list1));
    }
}
