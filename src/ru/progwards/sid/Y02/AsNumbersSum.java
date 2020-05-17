package ru.progwards.sid.Y02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//  собственная версия
public class AsNumbersSum {
    String rezult = "";
    List<Integer> akk = new ArrayList<>();
    public static String asNumbersSum(int number){
        AsNumbersSum X1 = new AsNumbersSum();
        X1.akk.get(number);
        X1.genSet(X1.akk, number);
        return X1.rezult;    //.substring(0, X1.rezult.length()-1);
    }

    public String genSet(List<Integer> lst, int number){
        // условие выхода
//        if (number == 0)
//            return "0";
        if (number == 1 )
            return "1";
        if (number == 2 )
            return "[1+1]";
        System.out.println("До = " + number +" ------------------------");
        String z = "";
        String nextX = "";
        String nextY = "";
        for (int i = 1; i <= number/2; i++) {
            int x = number - i;
            int y = i;
            //  создать пару (x, y)
            akk.add(x);
            akk.add(y);
            //  вызвать genSet для каждого элемента коллекции
//            System.out.println("До = " + number +" - i = " + i + "->" + x + "+" + y + "~" + z);
            z = z + "(" + String.valueOf(x)+ "+" + String.valueOf(y) + ") ~ " ;
            if (x == 1)
                nextX = "1";
            else
                nextX = genSet(lst, x);
            nextY = genSet(lst, y);
            z = z + nextX + "+" + nextY + "#";
            System.out.println(z);
//            System.out.println("По = " + number +" - i = " + i + "->" + x + "+" + y + "~" + z);
//            System.out.println(x);
        }
        System.out.println("По = " + number +" ========================");
        System.out.println(z);
        return z;
    }
    int asSplit(int subNum){
        return 0;
    }
    //  5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1
    public static void main(String[] args) {
//        System.out.println(asNumbersSum(5));
        Integer[] ar = {1 , 2, 3};
        System.out.println(array2queue(ar));
    }

    static ArrayDeque<Integer> array2queue(Integer[] a) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque();
//        Collections.addAll(arrayDeque, a);
        for (int i = 0; i < a.length; i++) {
            arrayDeque.add(a[i]);
        }
        return arrayDeque;
    }
}
