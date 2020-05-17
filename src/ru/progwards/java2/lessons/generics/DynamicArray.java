package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

//  Задача 2. Реализовать класс, DynamicArray - обобщающий динамический массив, растущий блоками,
//  на основе обычного статического массива. Стратегия роста - при полном заполнении текущего объема,
//  новый размер массива должен быть в 2 раза больше предыдущего.
public class DynamicArray<T> {
    private T[] array; // массив объектов T
    int size;       // реальное заполнение

    public DynamicArray(){ // конструктор по умолчанию
        array = (T[]) new Object[0];   //  Вопрос 1 - по другому можно?
        size = 0;
    }
    //  --------------------------------
    //  добавляет элемент в конец массива
    public void add(T num){
        if (array.length == size) {
            int newSize = (size == 0) ? 1 : array.length * 2;  //   удвоить размер
//            System.out.println("Увеличение массива до - " + newSize);
            Object[] newArray = new Object[newSize];
            if (size > 0)
                System.arraycopy(array, 0, newArray, 0, size);
            array = (T[]) newArray;
        }
        array[size++] = num;
    }
    //  --------------------------------
    //  добавляет элемент в заданную позицию позицию массива
    public void insert(int pos, T num){
        if (pos > size)
        {System.out.println("pos = " + pos + " > размера массива = " + (size) ); return;}
        if (array.length == size) {
            //  требуется увеличить массив
            int newSize = (size == 0) ? 1 : array.length * 2;  //   удвоить размер
            Object[] newArray = new Object[newSize];
            if (size > 0) {
                if (pos == 0)
                    System.arraycopy(array, 0, newArray, 1, size);
                else {
                    System.arraycopy(array, 0, newArray, 0, pos);
                    System.arraycopy(array, pos, newArray, pos+1, size-pos);
                }
            }
            array = (T[]) newArray;
        }
        else {
            //  перекопировать части старого массива
            System.arraycopy(array, pos, array, pos+1, size-pos);
        }
        array[pos] = num;
        size++;
    }
    //  --------------------------------
    //  удаляет элемент в позиции pos массива
    //  ArrayIndexOutOfBoundsException - удаление индекса > length
    public void remove(int pos){
        if (size == 0)
            {System.out.println("длина массива = " + (size) ); return;}
        if (pos >= size)
            {System.out.println("pos = " + pos + " > размера массива = " + (size) ); return;}

        int newSize = size - 1;
        if (pos == 0) {
            System.arraycopy(array, 1, array, 0, newSize);
            array[newSize] = null;  //  для ясности, чтобы не оставлять в ячейке прежнее значение
        }
        else {
            if (pos == newSize)
                array[newSize] = null;  //  для ясности, чтобы не оставлять в ячейке прежнее значение
            else {
                System.arraycopy(array, pos+1, array, pos, newSize-pos);
                array[newSize] = null;  //  для ясности, чтобы не оставлять в ячейке прежнее значение
            }
        }
        size--;
    }
    //  --------------------------------
    //  возвращает элемент по индексу pos
    public T get(int pos){
        if (pos >= 0 && pos < size)
            return array[pos];
        else
            return null;
//            return Integer.MAX_VALUE;
    }
    //  --------------------------------
    //  возвращает текущий реальный объем массива
    public int size() {
        return size;
    }
    //  --------------------------------
    public static void main(String[] args) {
        final int count1 = 9;
        DynamicArray din = new DynamicArray();

        long start = System.currentTimeMillis();
        for(int i=0; i<count1; i++)
            din.add(i+i);

        long middle = System.currentTimeMillis();
        System.out.println("DynamicArray <add> time = "+(middle-start));
        System.out.println("din.length = " + din.array.length);
        System.out.println("din.size() = " + din.size());
        System.out.println(Arrays.toString(din.array));

        for(int i=0; i<count1; i++)
//            din.atDelete(din.size-1);
            din.insert(0, i*i);
        long stop = System.currentTimeMillis();
        System.out.println("DynamicArray <ins> time ="+(stop-middle));

//        din.atInsert(0,1);
//        din.add(1);
//        din.add(2);
//        din.add(3);
//        din.add(4);
//        din.add(5);
//        din.add(6);
//        din.atDelete(0);
        System.out.println("din.length = " + din.array.length);
        System.out.println("din.size() = " + din.size());
//        System.out.println(Arrays.toString(din.array));
//        System.out.println(din.at(0));
//        din.atDelete(0);
//        din.atDelete(2);
//        din.atDelete(5);
        System.out.println(Arrays.toString(din.array));
//        System.out.println("din.length = " + din.array.length);
//        System.out.println("din.size() = " + din.size());

        System.out.println(din.get(0));
        System.out.println(din.get(count1 - 1));
        System.out.println(din.get(din.array.length+100));   //  ???
//        din.atDelete(0);
    }
}
