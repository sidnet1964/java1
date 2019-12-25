package ru.progwards.sid;

public class HelloWorld {
    public static void main(String[] args) {
    //  конвертация чисел в строки
//        String str11 = Integer.toString(987654321);
//        Integer intObj11 = 123456789;
//        String str21 = intObj11.toString();
//        System.out.println(str11);
//        System.out.println(str21);
        System.out.println(factorial(5));
     }
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