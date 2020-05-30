package ru.progwards.java2.lessons.basetypes;
import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int ind;

    ArrayIterator(T[] array) {
        this.array = array;
        ind = -1;
    }

    @Override
    public boolean hasNext() {
        if (ind < array.length-1) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        ind++;
        try {
            return array[ind];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
//        String[] names = {"Александр", "Борис", "Василий", "Василий", "Василий", "Григорий", "Василий", "Григорий", "Василий", "Борис", "Григорий", "Дмитрий", "Александр", "Дмитрий", "Борис", "Григорий"};
        String[] names = {"Александр", "Борис"};
        ArrayIterator<String> a_n = new ArrayIterator<String>(names);
        if  (a_n.hasNext())
            System.out.println(a_n.next());
        if  (a_n.hasNext())
            System.out.println(a_n.next());
        if  (a_n.hasNext())
            System.out.println(a_n.next());
    }
}
