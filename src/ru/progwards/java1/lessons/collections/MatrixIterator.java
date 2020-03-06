package ru.progwards.java1.lessons.collections;
import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T>{
    private T[][] array;
    private int ind;
    private int x;  //  количество строк
    private int y;  //  количество столбцов
    private int z;  //  общеее количество

    MatrixIterator(T[][] array) {
        this.array = array;
        x = array.length;
        if (x > 0)
            y = array[0].length;    //  предполагаем, что есть хоть одна строка;
        else
            y = 0;
        z = x * y;
        ind = -1;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        if (ind < z-1) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        // TODO Auto-generated method stub
        ind++;
        try {
            int d1 = ind / y;
            int d2 = ind - (d1 * y);
            return array[d1][d2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        String[][] names = {{"Александр", "Анна", "Борис"}, {"Бэлла", "Василий", "Василиса"}};
//        String[] names = {"Александр", "Борис"};
        MatrixIterator<String> a_n = new MatrixIterator<String>(names);
        if  (a_n.hasNext())
            System.out.println(a_n.next());
        if  (a_n.hasNext())
            System.out.println(a_n.next());
//        System.out.println(a_n.next());
//        System.out.println(a_n.next());
        if  (a_n.hasNext())
            System.out.println(a_n.next());
        if  (a_n.hasNext())
            System.out.println(a_n.next());
        if  (a_n.hasNext())
            System.out.println(a_n.next());
    }
}
//  {"Александр","Анна"}, {"Борис", "Бэлла"}, {"Василий", "Василиса"}