package ru.progwards.java1.lessons.collections;
import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T>{
    private T[][] array;
    private int ind;
    private int x;  //  текущая строка
    private int y;  //  текущий столбец
    private int z;  //  общеее количество

    MatrixIterator(T[][] array) {
        this.array = array;
        x = 0;
        y = -1;
        z = 0;
        //  просчитать общеее количество элементов
        for (int i=0; i<array.length; i++)
            for (int j=0; j<array[i].length; j++)
                z++;
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
            if (y < array[x].length-1) {
                y++;
            }
            else{
                x++;
                y = 0;
            }
            return array[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        String[][] names = {{"Александр"}, {"Анна", "Борис"}, {"Бэлла"}, {"Василий", "Василиса", "Виктор"}};
//        String[] names = {"Александр", "Борис"};
        MatrixIterator<String> a_n = new MatrixIterator<String>(names);
        if  (a_n.hasNext())            System.out.println(a_n.next());
        if  (a_n.hasNext())            System.out.println(a_n.next());
        if  (a_n.hasNext())            System.out.println(a_n.next());
        if  (a_n.hasNext())            System.out.println(a_n.next());
        if  (a_n.hasNext())            System.out.println(a_n.next());
        if  (a_n.hasNext())            System.out.println(a_n.next());
        if  (a_n.hasNext())            System.out.println(a_n.next());
    }
}
//  {"Александр","Анна"}, {"Борис", "Бэлла"}, {"Василий", "Василиса"}