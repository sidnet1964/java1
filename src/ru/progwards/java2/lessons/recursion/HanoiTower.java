package ru.progwards.java2.lessons.recursion;

import java.util.ArrayDeque;
import java.util.Arrays;

public class HanoiTower {
    final static int p3 = 3;  //  для чистоты кода
    int size;
    int pos;
    ArrayDeque<Integer>[] pin;  //  стек на штыре
    //  --------------------------------
    //  конструктор
    public HanoiTower(int size, int pos) {
        this.size = size;
        this.pos = pos;
        ArrayDeque<Integer> pin1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> pin2 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> pin3 = new ArrayDeque<Integer>();
//        int[] pin = new int[3]; //  массив штырей
        pin = new ArrayDeque[]{pin1, pin2, pin3}; //  массив штырей
        for (int i = size; i>0; i--)    //  внизу кольцо size, вверху - 1
            pin[pos].addFirst(i);
        System.out.println(pin[pos]);
//  Stack<MyClass>[] arr = new Stack<MyClass>[10];
//  Stack<Object>[] arr = (Stack<Object>[]) new Stack<?>[10];
//        input       = new ArrayDeque<>();
    }
    //  --------------------------------
    //  --------- перестановки с from на to
    //  переносит башню со штыря from на штырь to
    public void moveX(int from, int to){
        int round = 0;  //  кольцо для переноса
        int rFr = 0; int rTo = 0;
        boolean bFr = true;
        boolean bTo = false;
        if (pin[from].peekFirst() == null)
            bFr = false;
        else {
            rFr = pin[from].peekFirst();  //  посмотреть, что лежит сверху
            if (pin[to].peekFirst() == null) {
                bTo = true;
                round = pin[from].removeFirst();    //  снать кольцо
                pin[to].addFirst(round);            //  положить кольцо
            }
            else {
                rTo = pin[to].peekFirst();  //  посмотреть, что лежит сверху
                if (rFr < rTo){
                    round = pin[from].removeFirst();    //  снать кольцо
                    pin[to].addFirst(round);            //  положить кольцо
                }
            }
        }
        System.out.println(pin[from].peekFirst() + " -> " + pin[to].peekFirst() );
//        System.out.println(rFr + " -> " + rTo );
    }
    //  --------------------------------
    void print() {
        //  преобразовать стеки в массивы (полномерные размером size)
        Integer[][] ar2 = new Integer[p3][];
        for (int i = 0; i<p3; i++) {
            ArrayDeque<Integer> pinX = pin[i];
//            System.out.println(pinX.size());
            Integer[] arrInt = pinX.toArray(new Integer[pinX.size()]);  //  зубчатый массив
//            Integer[] arrInt = pinX.toArray(new Integer[size]); //  для прямоугольного массива, пустые - null
            ar2[i] = arrInt;
        }
        System.out.println(Arrays.deepToString(ar2));
        //  цикл по горизонталям
        for (int i = 0; i<size; i++){
            String line = "";
            //  цикл по штырям (всего их 3)
            for (int j = 0; j<p3; j++) {
                int shift = size - ar2[j].length;   //  пропуск верхних ячеек
                if (shift > i)
                    line = line + "  |  " + "_";
                else
                    line = line + "<00" + ar2[j][i-shift] + ">" + "_";
            }
            System.out.println(line.substring(0,line.length()-1));
        }
        System.out.println("=====+=====+=====");
    }
    //  включает отладочную печать состояния игрового поля после каждого шага алгоритма
    //  (метода moveX). В результате все промежуточные ходы должны быть отображены
    void setTrace(boolean on){

    }
    //  --------------------------------
    public static void main(String[] args) {
        HanoiTower tower = new HanoiTower(5,0);
        tower.print();
        tower.move(0,2);
        tower.print();
    }
    public void move(int from, int to){
        moveStack(from, to, this.size);
    }
    //  --------------------------------
    void moveStack(int from, int to, int count) {
        int othe = -1;
//        System.out.println("+++=" + count + "=->=" + from + "=" + othe + "=" + to);
        if (count > 0){
            othe = getIndex(from, to);
            moveStack(from, othe,count-1); // перенести башню из n−1 диска на 2-й штырь
            ArrayDeque from_X = this.pin[from];
            ArrayDeque to_X = this.pin[to];
            int top = (int) from_X.pollFirst();
            to_X.addFirst(top);
//            int biggest = from.pop();
//            System.out.println(">>>=" + biggest);
//            to.push(biggest); // переносим самый большой диск на 3-й штырь
            //  отладочная печать
            this.print();
            moveStack(othe,to,count-1); // перенеси башню из n−1 диска на 3-й штырь
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
}
