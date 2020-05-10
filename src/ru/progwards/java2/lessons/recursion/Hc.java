package ru.progwards.java2.lessons.recursion;

public class Hc {
    static void hanoy(int num, char a, char b, char c){
        System.out.println(num + " = " + "<" + a+b+c+">");

        if (num > 0){
            hanoy(num-1,a,c,b);
            System.out.println("---> " + a + c);
            hanoy(num-1,b,a,c);
        }
    }

    public static void main(String[] args) {

        char a,b,c;
        int num;
        a='A'; b='B'; c='C'; num=3;

        hanoy(num,a,b,c);

//        getch();
        System.out.println();
        System.out.println(num);
        System.out.println("<"+a+b+c+">");

    }
}
