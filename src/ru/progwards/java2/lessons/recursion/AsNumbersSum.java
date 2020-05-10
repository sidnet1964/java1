package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {
    public static String asNumbersSum(int number){
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
            int x = i;
            int y = number - i;
//            System.out.println("До = " + number +" - i = " + i + "->" + x + "+" + y + "~" + z);
            z = z + "(" + String.valueOf(x)+ "+" + String.valueOf(y) + ") ~ " ;
            if (x == 1)
                nextX = "1";
            else
                nextX = asNumbersSum(x);
            nextY = asNumbersSum(y);
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
        System.out.println(asNumbersSum(5));
    }
}
