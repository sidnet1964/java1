package ru.progwards.sid.HashSet;
import java.util.*;

public class SetIterator {
    final static String TEXT = "на дворе трава на траве дрова не руби дрова на траве двора";
    public static void main(String[] args) {
//  слайд 12 - Итератор в множествах
        Set<String> wordSet = new HashSet<>(Arrays. asList(TEXT.split(" ")));
        System. out.println(wordSet);
//  цикл for, основанный на итераторе
        for (String word : wordSet)
            System. out.print(word + " ");
        System. out.println();
//  перебор с помощью итератора
        Iterator<String> iter = wordSet.iterator();
        while (iter.hasNext())
            System. out.print(iter.next() + " ");
    }
}
