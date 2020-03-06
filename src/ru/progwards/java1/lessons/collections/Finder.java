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
//  найдите максимальное количество повторяющихся подряд элементов. Результат вернуть
//  в виде строки <элемент>:<количество>, например Василий:5.
    public static String findSimilar(Collection<String> names){
        String str_result = "";
        String str_max = null;
        int count_current = 0;
        int count_max = 0;
        for (String obj : names){
            if (obj.equals(str_max))
                count_current++;
            else {
                if (count_current > count_max)  //  новый максимум
                    str_result = str_max + ":"+Integer.toString(count_current); // текущмй результат
                str_max = obj;  //  новая последовательность
                count_current = 1;
            }
        }
    return str_result;
    }
    //  подготовка коллекции для работы
    public static Collection<Integer> fillFull(int n){
        Collection<Integer> list1 = new ArrayList<>();
        for (int i = 1; i < n+1; i++)
            list1.add(10 - i % 10); //
        return list1;
    }
    //  подготовка коллекции для работы
    public static Collection<String> fill_Str(int n){
        Collection<String> list1 = new ArrayList<>();
        for (int i = 1; i < n+1; i++)
            list1.add("-"+Integer.toString(10 - i % 10)+"-");
        return list1;
    }

    public static void main(String[] args) {
//        List<Integer> list1 = new ArrayList<>();
//        Collection<Integer> list1 = fillFull(22);
//        Collection<String> list1 = fill_Str(22);
        List<String> list1 = (List<String>) fill_Str(22);
        list1.sort(null);
        System.out.println(list1);
//        System.out.println(Arrays.toString(list1.toArray()));
//        System.out.println(findMinSumPair(list1));
//        System.out.println(findLocalMax(list1));
//        System.out.println(findSequence(list1));
        System.out.println(findSimilar(list1));
    }
}
