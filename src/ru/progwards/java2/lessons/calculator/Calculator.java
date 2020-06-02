package ru.progwards.java2.lessons.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static int calculate(String expression){
        int position = -1;
        int rezult = 0;
        //  обработка скобок
        expression = brackets(expression);
        //  найти знак сложения +
        position = patternIndexOf(expression, "[+-]");
        if (position > 0){
            //  может быть и 0 для унарного оператора
            rezult = operation(position, expression);
        }
        else {
            //  найти знак умножения *
            position = patternIndexOf(expression, "[*/]");
            if (position > 0){
                rezult = operation(position, expression);
            }
            else
                rezult = Integer.parseInt(expression);
        }
        return rezult;
    }
    //  --------------------------------
    //  обработка вложенных скобок
    static String brackets(String exp) {
        int p1 = patternIndexOf(exp, "[(]");
        if (p1 >= 0) {
            //  посмотреть, есть ли еще скобки?
            int p2 = patternIndexOf(exp.substring(p1 + 1), "[()]");
            if (p2 >= 0) {
                //  а что же за скобка у нас объявилась?
                if (exp.charAt(p1 + 1 + p2) == ')') {
                    String subExpression = exp.substring(p1 + 1, p1 + 1 + p2);
                    System.out.println(subExpression);
                    String subInteger = String.valueOf(calculate(subExpression));
                    exp = exp.replace("(" + subExpression + ")", subInteger);
                } else {
                    String prefix = exp.substring(0, p1 + 1 + p2);  //  без второй (
                    String suffix = exp.substring(p1 + 1 + p2);     //  включая вторую (
                    exp = prefix + brackets(suffix);
                }
            } else
                throw new IndexOutOfBoundsException("Нет закрывающейся скобки");
            return brackets(exp);   //  попытка 2
        }
        return (exp);
    }
    //  --------------------------------
    //  выполнить одну операцию
    static int operation(int position, String expression){
        int rezult = 0;
        char op = expression.charAt(position);
        String le = expression.substring(0, position);   //  левая часть
        String ri = expression.substring(position+1);    //  правая часть

        System.out.println(le + "|"+op+"|" + ri);
        rezult = switch (op) {
            case '+' -> calculate(le) + calculate(ri);
            case '-' -> calculate(le) - calculate(ri);
            case '*' -> calculate(le) * calculate(ri);
            case '/' -> calculate(le) / calculate(ri);
            default -> 0;
        };
        return rezult;
    }
    //  --------------------------------
    //  поиск знаков по шаблону
    static int patternIndexOf(String inputString, String pattern) {
        Matcher m = Pattern.compile(pattern).matcher(inputString);
        if (m.find()) {
            return m.start();
        } else {
            return -1;
        }
    }
    public static void main(String[] args) {
        System.out.println(calculate("((1)+2)*((2+3))"));
    }
}
