package ru.progwards.sid;

public class PrimerParseInt {
    public static void main(String[] args) {
        String str1 = "999";
        String str2 = "000555";
        String str3 = "999.555";
        Integer intObj1 = 5;
        //  int int1 = intObj1.intValue();  // распаковка явная
        int int1 = intObj1;                 // автораспаковка
        //  double double1 = intObj1.doubleValue();
        double double1 = intObj1;
        System.out.println(int1);
        System.out.println(double1);
        Short s1 = Short.valueOf(str1);
        int i1 = Integer.valueOf(str2);
        int i2 = Integer.parseInt(str2);
        double d1 = Double.valueOf(str3);
        double d2 = Double.parseDouble(str3);
        System.out.println(s1);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(d1);
        System.out.println(d2);
        //  конвертация чисел в строки
        String str11 = Integer.toString(987654321);
        Integer intObj11 = 123456789;
        String str21 = intObj11.toString();
        System.out.println(str11);
        System.out.println(str21);
    }
}
