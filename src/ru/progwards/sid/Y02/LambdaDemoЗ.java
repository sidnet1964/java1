package ru.progwards.sid.Y02;

public class LambdaDemoЗ {
    public static void main(String[] args) {
        // В этом лямбда-выражении проверяется, является ли одно число множителем другого
        NumericTest2 isFactor = (n, d) -> (n % d) == 0;
        isFactor = (int n, int d) -> (n % d) == 0; //   можно так
        if(isFactor.test(10, 2))
            System.out.println("Чиcлo 2 является " + "множителем числа 10");
        if(!isFactor.test(10, 3))
            System.out.println("Чиcлo 3 не является" + "множителем числа 10");
    }
}
