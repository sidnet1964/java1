package ru.progwards.java1.lessons.bigints;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

//  Задача 1. Класс BigAlgebra
public class BigAlgebra {
    //  алгоритм быстрого возведения в степень pow числа num в BigDecimal
    static BigDecimal fastPow(BigDecimal num, int pow){
        BigDecimal result = BigDecimal.ONE;
        //  Представить показатель степени pov в двоичном виде
        String BinaryString = Integer.toBinaryString(pow);
        char[] strToArray = BinaryString.toCharArray(); // Преобразуем строку str в массив символов (char)
        System.out.println(Arrays.toString(strToArray));
        for(int i = 0; i < strToArray.length; i++) {
            if (strToArray[i] == '1') {
                result = result.pow(2);
                result = result.multiply(num);
                }
            else {
                result = result.pow(2);
            }
        }
        System.out.println();
        return result;
    }
    //  алгоритм вычисления n-го числа Фибоначчи в BigInteger
    static BigInteger fibonacci(int n){
        BigInteger f_n_2 = BigInteger.ZERO;  //  F[n-2]
        BigInteger f_n_1 = BigInteger.ONE;  //  F[n-1]
        BigInteger f_n = BigInteger.ONE;    //  F[n]
        for (int i = 2; i <= n; i++){
            f_n = f_n_2.add(f_n_1); //  f_n = f_n_2 + f_n_1;
            f_n_2 = f_n_1;
            f_n_1 = f_n;
        }
        return f_n;
    }
    public static void main(String[] args) {
//        System.out.println(fastPow(new BigDecimal("0.2"), 100));
        System.out.println(fibonacci(10));
    }
}
