package ru.progwards.sid;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorForLinkedList {
    static final int ELEMENTS_COUNT = 50;
    public static void main(String args[]) {
        List<Integer> linkedList = new LinkedList();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            linkedList.add(0,i + 1);
        }
        System.out.println("Результат с итератором:");
        for (Iterator<Integer> iterator = linkedList.iterator(); iterator.hasNext(); ) {
            Integer intObj = iterator.next();
            if (intObj % 2 == 0)
                iterator.remove();  //  проверка метода
            System.out.println("Значение элемента = " + intObj);
        }
        System.out.println("\nРезультат с for-each:");
        for (Integer intObj : linkedList) {
            System.out.println("Значение элемента = " + intObj);
        }
    }
}
