package ru.progwards.sid;

import java.util.*;

public class N12 {
    public static List<Integer> listAction(List<Integer> list) {
        list.remove(Collections.min(list));
        list.add(0, list.size());
        list.add(2, Collections.max(list));
        return list;
    }

    public static List<Integer> filter(List<Integer> list) {
        int result = 0;
        for (Integer i : list)
            result += i;
        for (int i = list.size() - 1; i >= 0; i--)
            if (list.get(i) >= result / 100)
                list.remove(i);

        return list;
    }

    public static void iterator3(ListIterator<Integer> iterator){
        for (; iterator.hasNext(); ) {
            Integer intObj = iterator.next();
            if (intObj % 3 == 0) {
                int ind = iterator.nextIndex() - 1 ;
                iterator.set(ind);
            }
        }
//        for (; iterator.hasNext(); ) {
//            Integer intObj = iterator.next();
//            System.out.println("Значение элемента = " + intObj);
//        }
    }

    public static void main(String[] args) {
        List<Integer> linkedList = new ArrayList();
        for (int i = 0; i < 50; i++)
            linkedList.add(i);
        ListIterator<Integer> iterator = linkedList.listIterator();
        iterator3(iterator);
//        System.out.println(filter(list5));
//        System.out.println(listAction(list5));
//        Collection<Integer> numbers = new ArrayList();
//        for(int i=0; i<5; i++)
//            numbers.add(i);
//        ((ArrayList)numbers).add(3, numbers.size());
//        numbers.add(Collections.min(numbers));
//        numbers.add(3, 5);
//        numbers.remove(3);
//        System.out.println(numbers);
//        List<Integer> linkedList = new LinkedList();
//        for (int i = 0; i < 5; i++)
//            linkedList.add(i);
//---------------------------------------
//        for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); ) {
//            Integer n = listIterator.next();
//            if (n % 2 != 0)
//                listIterator.set(n * 2);
//        }
//        for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); ) {
//            Integer n = listIterator.next();
//            if (n % 2 != 0)
//                listIterator.remove();
//            else
//                listIterator.add(n * 2);
//        }
//        System.out.println(linkedList);

    }
}