package ru.progwards.sid.Y02;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + " " + age;
    }
    static void sortAndPrint(List<Person> list){
        list.sort((a, b) -> Double.compare(a.age, b.age));
        list.forEach(element -> System.out.println(element));
    }
    static int sumSequence(int n) {
        if (n == 1)
            return n;
        return sumSequence(n-2)+n;
    }
//  возвращает символы строки str в обратном порядке. Т.е. если на входе "12345"
//  на выходе должно быть "54321"
static String reverseChars(String str){
        if (str == null)
            return null;
        if (str.length() <= 1 )
            return str;
        return reverseChars(str.substring(1)) + str.charAt(0);
    }
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>(List.of( 
                new Person("Пушкин", 54), 
                new Person("Достоевский", 57), 
                new Person("Лермонтов", 59), 
                new Person("Гоголь", 84), 
                new Person("Маяковский", 49) ));
//        sortAndPrint(list);
//        System.out.println(sumSequence(3));
        System.out.println(reverseChars(null        ));
    }
}
