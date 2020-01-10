package ru.progwards.sid;

public class Сompare {
    public static void main(String[] args) {
//        Object obj1 = new Object();
//        Object obj2 = new Object();
//        System.out.println(obj1 == obj2);       //  false
//        System.out.println(obj1.equals(obj2));  //  false
//        obj2 = obj1;
//        System.out.println(obj1 == obj2);       //  true
//        System.out.println(obj1.equals(obj2));  //  true
//  ---------
//        String str1 = "12345";
//        String str2 = "12345";
//        System.out.println(str1 == str2);       //  true
//        System.out.println(str1.equals(str2));  //  true
//  ---------
//        String str1 = new String("12345");
//        String str2 = new String("12345");
//        System.out.println(str1 == str2);       //  false
//        System.out.println(str1.equals(str2));  //  true
//  ---------
        String str1 = "12345";
        String str2 = "1234";
        str2 += "5";
        System.out.println(str1 == str2);       //  false
        System.out.println(str1.equals(str2));  //  true
//  ---------
    }
}

