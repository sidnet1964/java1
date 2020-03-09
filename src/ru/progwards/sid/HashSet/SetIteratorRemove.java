package ru.progwards.sid.HashSet;
import java.util.*;

public class SetIteratorRemove {
    final static String TEXT = "на дворе трава на траве дрова не руби дрова на траве двора";
    public static void main(String[] args) {
//  слайд 13 - Удаление элементов через итератор
        Set<String> wordSet = new HashSet<>(Arrays.asList(TEXT.split(" ")));
        System.out.println(wordSet);
        Iterator<String> iter = wordSet.iterator();
        while (iter.hasNext())
            if (iter.next().contains("трав"))
                iter.remove();  //  удалить элемент, полученный вызовом next()
        System.out.println(wordSet);
    }
}
