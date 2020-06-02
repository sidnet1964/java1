package ru.progwards.java2.lessons.recursion;

public class ListNumbersCount {
    public static int asNumbersSum(int number){
        ListNumbersCount X1 = new ListNumbersCount();
        return X1.dec(number, number, '*');
    }

    //  ----------------------------------------------
    int dec(int n, int k, char call) {
        System.out.println("<|0|" + n + "|" + k + "|" + call + "|[" +n + "," + k + "]");
        if (n < 0) {
            System.out.println(">|2|" + n + "|" + k + "|" + call + "|" + "0");
            return 0;
        }
        if (n <= 1 || k == 1) {
            System.out.println(">|3|" + n + "|" + k + "|" + call + "|" + "1");
            return 1;
        }
//        System.out.println(">|0|" + n + "|" + k + "|" + call + "|(" + n +"," + (k-1)+") + ("+ n +"-"+ k +"="+(n-k)+","+k+")" );
//        int intArr = dec(n, k - 1, '+') + dec(n - k, k,'-');
        System.out.println(">|0|" + n + "|" + k + "|" + call + "|(" + n +"-"+ k +"="+(n-k)+","+k+") + (" + n +"," + (k-1)+ ")");
        int intArr = dec(n - k, k,'-') + dec(n, k - 1, '+');
        System.out.println(">|4|" + n + "|" + k + "|" + call + "|" + intArr);
        return intArr;
    }
    //  ----------------------------------------------
    public static void main(String[] args) {
        int m = 6;
        System.out.println(asNumbersSum(m));
    }
}
// Определение Разбиением натурального числа n называется набор натуральных чисел λ=(λ1,…, λk),
// для которого λ1 >= λ2 >= … >= λk > 0 и λ1+…+λk =n.
