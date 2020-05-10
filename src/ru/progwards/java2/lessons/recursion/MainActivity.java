package ru.progwards.java2.lessons.recursion;

import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;
import org.w3c.dom.ls.LSOutput;

import java.util.Stack;
//  java - Рекурсия, задача про ханойские башни - Stack Overflow на русском
public class MainActivity {
    public static void main(String[] args) {
        Stack<Integer> from = new Stack<>();    //  штырь 1
        Stack<Integer> othe = new Stack<>();    //  штырь 2
        Stack<Integer> to = new Stack<>();      //  штырь 3
        from.push(3);   //  самый нижний
        from.push(2);   //  середина
        from.push(1);   //  самый верхний
//        System.out.println("print from = " + from);
//        exchange(from, othe, to, from.size());
//        System.out.println("print to = "+ to);
        moveStack(0,2, 3);
        System.out.println(getIndex(2,1));
    }

    private static void exchange(Stack<Integer> from, Stack<Integer> othe, Stack<Integer> to, int count) {
        System.out.println("+++=" + count + "=->=" + from + "=" + othe + "=" + to);
        if (count > 0){
            exchange(from,to,othe,count-1); // перенести башню из n−1 диска на 2-й штырь
            int biggest = from.pop();
            System.out.println(">>>=" + biggest);
            to.push(biggest); // переносим самый большой диск на 3-й штырь
            //  отладочная печать
            exchange(othe,from,to,count-1); // перенеси башню из n−1 диска на 3-й штырь
        }
        System.out.println("---=" + count + "=->=" + from + "=" + othe + "=" + to);
    }
    private static void moveStack(int from, int to, int count) {
        int othe = -1;
        System.out.println("+++=" + count + "=->=" + from + "=" + othe + "=" + to);
        if (count > 0){
            othe = getIndex(from, to);
            moveStack(from, othe,count-1); // перенести башню из n−1 диска на 2-й штырь
//            int biggest = from.pop();
//            System.out.println(">>>=" + biggest);
//            to.push(biggest); // переносим самый большой диск на 3-й штырь
            //  отладочная печать
            moveStack(othe,to,count-1); // перенеси башню из n−1 диска на 3-й штырь
        }
        System.out.println("---=" + count + "=->=" + from + "=" + othe + "=" + to);
    }

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
