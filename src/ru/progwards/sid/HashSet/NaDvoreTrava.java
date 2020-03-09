package ru.progwards.sid.HashSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NaDvoreTrava {
    final static String TEXT = "на дворе трава на траве дрова не руби дрова на траве двора";
    public static void main(String[] args) {
//  слайд 8 - создание постого множества срокового типа и добавление в него нового элемента
//        Set<String> stringSet = new HashSet<>();
//        stringSet.add( "добавим");    //  boolean add()
//        System. out.println(stringSet);
//  слайд 9 - HashSet для отбора уникальных слов
        String[] words = TEXT.split(" ");       //  разделить строку на слова и записать в массив
        List<String> wordList = Arrays.asList(words);   //  преобразовать массив в список
        // (с фиксированным размером, без команд добавить, удалить), это не ArrayList
//  второй вариант - wordList будет иметь тип ArrayList
//List<String> wordList = new ArrayList<>(Arrays.asList(words));
        Set<String> wordSet = new HashSet<>(wordList);  //  формирование множества из списка
        System. out.println(wordList);
        System. out.println(wordSet);
    }
}
