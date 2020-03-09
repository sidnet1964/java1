package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOperations {
    public Integer id;
    SetOperations (Integer id) {
        this.id = id;
    }
    //  объединение множеств
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.addAll(set2);
        return result;
    }
    //  пересечение множеств
    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.retainAll(set2);
        return result;
    }
//  Разность двух множеств — теоретико-множественная операция, результатом
//  которой является множество, в которое входят все элементы первого множества,
//  не входящие во второе множество.
    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.removeAll(set2);
        return result;
    }
//  Симметрическая разность двух множеств — теоретико-множественная операция,
//  результатом которой является новое множество, включающее все элементы исходных
//  множеств, не принадлежащие одновременно обоим исходным множествам.
    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
        HashSet result1 = new HashSet(set1);
        HashSet result2 = new HashSet(set1);
        result1.addAll(set2);       //  объединение множеств
        result2.retainAll(set2);    //  пересечение множеств
        result1.removeAll(result2);
        return result1;
        }
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.addAll(List.of(0, 1, 3, 5, 7, 9));
        Set<Integer> set2 = new HashSet<>();
        set2.addAll(List.of(0, 1, 2, 4, 6, 8));
        System.out.println(union(set1, set2));
        System.out.println(intersection(set1, set2));
        System.out.println(difference(set1, set2));
        System.out.println(symDifference(set1, set2));
    }
}
