package ru.progwards.java2.lessons.basetypes;

public class DoubleTest {
    public static void main(String[] args) {
        DoubleHashTable<Integer,String> table = new DoubleHashTable(5);
        System.out.println(table);
        for (int i=0; i<15; i++)
            table.add(i, "i="+i);
        System.out.println(table);
        System.out.println(table.size());
        table.remove(101);
        System.out.println(table);
        System.out.println(table.size());
        for (Integer inic : table)
            System.out.println(inic);
//        table.add(1, "111");
//        table.change(0, 101);
//        for (int i=0; i<15; i++)
//            System.out.println(table.get(i));
//
//        System.out.println(table.size());
//        System.out.println(table);
    }
}
