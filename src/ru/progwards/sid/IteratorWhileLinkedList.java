package ru.progwards.sid;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorWhileLinkedList {
    static final int ELEMENTS_COUNT = 500;
    public static void main(String args[]) {
        List<Integer> linkedList = new LinkedList();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            linkedList.add(0,i + 1);
            }
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer intObj = iterator.next();
            iterator.remove();  //  проверка метода
            System.out.println("Значение элемента = " + intObj);
        }
    }
}
