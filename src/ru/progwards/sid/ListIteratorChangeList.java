package ru.progwards.sid;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorChangeList {
    static final int ELEMENTS_COUNT = 50;
    public static void main(String args[]) {
        List<Integer> linkedList = new LinkedList();
        for (int i = 0; i < ELEMENTS_COUNT; i++)
            linkedList.add(i + 1);
        System. out.println( "Список до изменения:");
        for (Integer intObj : linkedList)
            System. out.println( "Значение элемента = " + intObj);

        System. out.println( "\nСписок после изменения:");
        for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); ) {
            Integer intObj = listIterator.next();
            int ind = listIterator.nextIndex();
            listIterator.set(intObj * intObj * intObj);
//            Integer intObj3 = listIterator.toString();
//            System. out.println( ind + " Значение элемента = " + intObj3);
        }

        for (Integer intObj : linkedList)
            System. out.println( "Значение элемента = " + intObj);
    }
}
