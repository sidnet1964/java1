package ru.progwards.sid.Y02;

public class LambdaDemo2 {
    public static void main(String[] args) {
        // Лямбда-выражение, в котором проверяется, является ли число четным
        NumericTest isEven = n -> (n % 2)==0;
        isEven = (int n) -> (n % 2)==0; // можно также с типом, но нельзя опускать скобки
        if(isEven.test(10))
            System.out.println("Чиcлo 10 четное");
        if(!isEven.test(9))
            System.out.println("Чиcлo 9 нечетное");
        // А теперь воспользоваться лямбда-выражением, в котором проверяется,
        // является ли число неотрицательным
        NumericTest isNonNeg = (n) -> n >= 0;
        if(isNonNeg.test(1))
            System.out.println("Чиcлo 1 неотрицательное");
        if(!isNonNeg.test(-1))
            System.out.println("Чиcлo -1 отрицательное");
    }
}
