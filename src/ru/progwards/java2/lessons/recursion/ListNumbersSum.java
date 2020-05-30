package ru.progwards.java2.lessons.recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class ListNumbersSum {
    static int[] intArr = new int[10];
    static ArrayList<Integer> intList = new ArrayList<>();
    String rezult = "";
    public static String asNumbersSum(int number){
        ListNumbersSum X1 = new ListNumbersSum();
        X1.genSet(number, number, 0, '*');
        return X1.rezult.substring(0, X1.rezult.length()-1);
    }
//  n - осталось набрать слагаемых на сумму n
//  k - слагаемые не больше k
//  i - номер очередного слагаемого
    //  ----------------------------------------------
    //  попробую через List
    void genSet(int n, int k, int i, char call) {
        System.out.println(n + "|" + k + "|" + i + "|" + call + "|" + Arrays.toString(intArr) + "|" + intList);
        if ( n < 0 )
            return;
        if ( n == 0 ) {
            for (int j = 0; j < i; j++) {
                rezult += intArr[j] + "+";
            }
            rezult = rezult.substring(0,rezult.length()-1) + "=";
        }
        else {
            if (n >= k) {
                intArr[i] = k;
                intList.add(i, k);
                genSet(n - k, k, i + 1, '+');
            }
            if (k > 1)
                genSet(n, k - 1, i, '#');
        }
    }
    //  ----------------------------------------------
    public static void main(String[] args) {
        int m = 5;
        System.out.println(asNumbersSum(5));
    }
}
// Определение Разбиением натурального числа n называется набор натуральных чисел λ=(λ1,…, λk),
// для которого λ1 >= λ2 >= … >= λk > 0 и λ1+…+λk =n.
