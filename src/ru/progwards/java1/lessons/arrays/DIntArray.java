package ru.progwards.java1.lessons.arrays;
import java.util.Arrays;

public class DIntArray {
    private int[] m0;    // массив чисел
    public DIntArray(){ // конструктор по умолчанию
//        int[] m0 = {1, 2, 3, 4, 5};   //  НЕ верно
//        m0 = new int[]{0};  //  верно
        m0 = new int[]{};  //  верно
    }
    public void add(int num){   //  добавляет элемент num в конец массива
        int [] m1 = Arrays.copyOf(m0, m0.length + 1);
        m1[m1.length-1] = num;
        m0 = m1;
    }
    public void atInsert(int pos, int num){
        int n0 = m0.length;
        if (pos > n0) System.out.println("pos = " + pos + " > длина массива = " + n0 );
        else {
            int[] m1 = new int[n0 + 1];  // удлиненный массив
            if (pos == 0)
                System.arraycopy(m0, 0, m1, 1, n0);
            else {
                System.arraycopy(m0, 0, m1, 0, pos);
                System.arraycopy(m0, pos, m1, pos+1, n0-pos);
            }
            m1[pos] = num;
            m0 = m1;
        }
    }
    public void atDelete(int pos){  // удаляет элемент в позиции pos массива
        int n0 = m0.length;
        if (n0 == 0)
            {System.out.println("длина массива = " + (n0) ); return;}
        if (pos >= n0)
            {System.out.println("pos = " + pos + " > позиции массива = " + (n0-1) ); return;}
        int[] m1 = new int[n0 - 1];  // укороченный массив
        if (pos == 0)
            System.arraycopy(m0, 1, m1, 0, n0-1);
        else {
            if (pos == n0 - 1)
                System.arraycopy(m0, 0, m1, 0, n0-1);
            else {
                System.arraycopy(m0, 0, m1, 0, pos);
                System.arraycopy(m0, pos+1, m1, pos, n0-pos-1);
            }
        }
        m0 = m1;
    }
    public int at(int pos){
        if (pos >= 0 && pos < m0.length)
            return m0[pos];
        else
            return Integer.MAX_VALUE;
    }
    public static void main(String[] args) {
        DIntArray din = new DIntArray();
        din.atInsert(0,1);
        din.add(7);
        din.atDelete(0);
        System.out.println(din.at(0));
    }
}
