package ru.progwards.java2.lessons.calculator;

public class Calculator {
    public static int calculate(String expression){
        int rezult = 0;
        int sign = -1;
        String left = "";
        String righ = "";
        //  найти знак умножения *
        sign = expression.indexOf('+');
        if (sign >= 0) {
            left = expression.substring(0, sign);
            righ = expression.substring(sign+1);
            System.out.println(left + "|+|" + righ);
            rezult = calculate(left) + calculate(righ);
        }
        else {
            sign = expression.indexOf('*');
            if (sign >= 0) {
                left = expression.substring(0, sign);
                righ = expression.substring(sign + 1);
                System.out.println(left + "|*|" + righ);
                rezult = Integer.valueOf(left) * Integer.valueOf(righ);
            }
            else
                rezult = Integer.valueOf(expression);
        }
        return rezult;
    }
    public static void main(String[] args) {
        System.out.println(calculate("2*5+3*2"));
    }
}
