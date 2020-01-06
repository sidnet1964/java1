package ru.progwards.sid;

public class Сompare {
    public static void main(String[] args) {
        String str1 = new String("12345");
        String str2 = new String("12345");
        System.out.println(str1.equals(str2));  //  true
        System.out.println(str1 == str2);       //  false
    }
}

