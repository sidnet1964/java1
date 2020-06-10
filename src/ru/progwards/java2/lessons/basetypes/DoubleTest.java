package ru.progwards.java2.lessons.basetypes;

public class DoubleTest {
    public static void main(String[] args) {
        final int en = 13;
        final int em = 999;
        DoubleHashTable<Integer,String> hTable = new DoubleHashTable(en);
//        System.out.println(hTable);
        for (int i=0; i < em; i++)
            hTable.add(i, "i=" + i);
        System.out.println(hTable);
        System.out.println(hTable.size());
        System.out.println(hTable.table.length);

//        hTable.remove(101);
//        System.out.println(hTable);
//        System.out.println(hTable.size());

//        for (var inic : hTable)
//            System.out.println(inic);
//        hTable.add(1, "111");
//        hTable.change(0, 101);
//        for (int i=0; i<15; i++)
//            System.out.println(hTable.get(i));
//
//        System.out.println(hTable.size());
//        System.out.println(hTable);
    }
}
