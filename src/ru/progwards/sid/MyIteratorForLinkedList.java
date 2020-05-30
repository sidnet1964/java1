package ru.progwards.sid;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyIteratorForLinkedList {
    static final int ELEMENTS_COUNT = 5;
    public static void main(String args[]) {
        List<Integer> linkedList = new LinkedList();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            linkedList.add(0,i + 1);
        }
        System.out.println("Результат с итератором:");
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer intObj = iterator.next();
            System.out.println("Значение элемента = " + intObj);
        }
    }
}
