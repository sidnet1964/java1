package ru.progwards.java2.lessons.recursion;

import java.util.ArrayDeque;
import java.util.Arrays;

import static java.lang.String.format;

//@SuppressWarnings("ALL")
public class HanoiTower {
    final static int p3 = 3;  //  для чистоты кода
    int size;
    int pos;
    ArrayDeque<Integer>[] pin;  //  массив стеков на штырях
    boolean setTrace;

    //  --------------------------------
    //  конструктор
    public HanoiTower(int size, int pos) {
        setTrace = false;
        this.size = size;
        this.pos = pos;
        ArrayDeque<Integer> pin1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> pin2 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> pin3 = new ArrayDeque<Integer>();
        pin = new ArrayDeque[]{pin1, pin2, pin3}; //  массив штырей
//        pin = new ArrayDeque<Integer>()[3]; //  массив штырей
        for (int i = size; i>0; i--)    //  внизу кольцо size, вверху - 1
            pin[pos].addFirst(i);
//        System.out.println(pin[pos]);
    }
    //  --------------------------------
    void print() {
        //  преобразовать стеки в массивы (полномерные размером size)
        Integer[][] ar2 = new Integer[p3][];
        for (int i = 0; i<p3; i++) {
            ArrayDeque<Integer> pinX = pin[i];
            Integer[] arrInt = pinX.toArray(new Integer[0]);  //  зубчатый массив
            ar2[i] = arrInt;
        }
        //  цикл по горизонталям
        for (int i = 0; i<size; i++){
            StringBuilder line = new StringBuilder();
            //  цикл по штырям (всего их 3)
            for (int j = 0; j<p3; j++) {
                int shift = size - ar2[j].length;   //  пропуск верхних ячеек
                if (shift > i)
                    line.append("  I  ").append(" ");
                else
                    line.append(format("<%03d>", ar2[j][i - shift])).append(" ");
            }
            System.out.println(line.substring(0,line.length()-1));
        }
        System.out.println("=================\n");
    }
    //  включает отладочную печать состояния игрового поля после каждого шага алгоритма
    //  (метода moveX). В результате все промежуточные ходы должны быть отображены
    void setTrace(boolean on){
        setTrace = on;   //  установить состояния
    }
    //  --------------------------------
    void moveStack(int from, int to, int count) {
        if (count > 0){
            int othe = getIndex(from, to);
            moveStack(from, othe,count-1); // перенести башню из n−1 диска на 2-й штырь
            ArrayDeque<Integer> from_X = this.pin[from];
            ArrayDeque<Integer> to_X = this.pin[to];
            int top = from_X.pollFirst();  // переносим верхний диск
            to_X.addFirst(top);
            if (setTrace)
                this.print();
            moveStack(othe,to,count-1); // перенеси башню из n−1 диска на 2-й штырь
        }
//        System.out.println("---=" + count + "=->=" + from + "=" + othe + "=" + to);
    }
    //  --------------------------------
    //  вычислить номер третьей оси по двум известным
    static int getIndex(int ind1, int ind2) {
        int res = 0;
//    Assert(ind1 <> ind2);
        switch (ind1) {
            case 0:
                if (ind2 == 1) res = 2;
                else res = 1;
                break;
            case 1:
                if (ind2 == 2) res = 0;
                else res = 2;
                break;
            case 2:
                if (ind2 == 0) res = 1;
                else res = 0;
                break;
//    Assert(False,'wrong indeces');
        }
        return res;
    }
    public void move(int from, int to){
        moveStack(from, to, this.size);
    }
    //  --------------------------------
    public static void main(String[] args) {
        HanoiTower tower = new HanoiTower(4,0);
        tower.print();
        tower.setTrace(true);
        tower.move(0,2);
        tower.print();
    }
}
