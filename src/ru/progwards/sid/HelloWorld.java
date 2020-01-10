package ru.progwards.sid;

public class HelloWorld {
    public static void main(String[] args) {
        byte value = (byte) 0b00001001;
        byte valu2 = (byte) 0b00001000;
        int test = (int) 0b00000000_00000000_00000000_00000001;
        int result = value & 0b00000000_00000000_00000000_00000001; // решение теста T7.2
        int result2 = valu2 & test;
    //  конвертация чисел в строки
//        String str11 = Integer.toString(987654321);
//        Integer intObj11 = 123456789;
//        String str21 = intObj11.toString();
//        System.out.println(str11);
//        System.out.println(str21);
        System.out.println(intToGrade(0));
     }
    static Grade intToGrade(int grade){
        switch (grade){
            case 1: return Grade.VERYBAD;
            case 2: return Grade.BAD;
            case 3: return Grade.SATISFACTORILY;
            case 4: return Grade.GOOD;
            case 5: return Grade.EXCELLENT;
            default: return Grade.NOTDEFINED;
        }
    }
    enum Grade{
        VERYBAD,
        BAD,
        SATISFACTORILY,
        GOOD,
        EXCELLENT,
        NOTDEFINED
    };
    static int addAsStrings(int n1, int n2){
        String s1 = Integer.toString(n1);
        String s2 = Integer.toString(n2);
        return Integer.parseInt(s1 + s2);
    }
    static String textGrade(int grade){
        String status;
        if (grade == 0)
            status = "не оценено";
        else if (grade >=1 && grade <=20)
            status = "очень плохо";
        else if (grade >=21 && grade <=40)
            status = "плохо";
        else if (grade >=41 && grade <=60)
            status = "удовлетворительно";
        else if (grade >=61 && grade <=80)
            status = "хорошо";
        else if (grade >=81 && grade <=100)
            status = "отлично";
        else
            status = "не определено";
        return status;
    }
    static long factorial(long n){
        long rez = 1;
        for (long i=1; i<=n; i++)
            rez *= i;
        return rez;
    }
}
//  https://github.com/sidnet1964/java1.git