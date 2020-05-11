package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {
    static int[] intArr = new int[100];
    String rezult = "";
    public static String asNumbersSum(int number){
        AsNumbersSum X1 = new AsNumbersSum();
        X1.genSet(number, number, 0);
        return X1.rezult.substring(0, X1.rezult.length()-1);
    }

//  n - осталось набрать слагаемых на сумму n
//  k - слагаемые не больше k
//  i - номер очередного слагаемого
    //  ----------------------------------------------
    void genSet(int n, int k, int i) {
        if ( n < 0 )
            return;
        if ( n == 0 ) {
            for (int j = 0; j < i; j++) {
//                System.out.print(intArr[j] + " ");
                rezult += intArr[j] + "+";
            }
//            System.out.println();
            rezult = rezult.substring(0,rezult.length()-1) + "=";
        }
        else {
            if ( n >= k) {
                intArr[i] = k;
                genSet(n - k, k, i + 1);
            }
            if ( k > 1)
                genSet(n, k - 1, i);
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
