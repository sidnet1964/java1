package ru.progwards.sid.N18;

import ru.progwards.java1.lessons.files.OneFile;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class Person {
    public String name;
    public Date birth;
    public double salary;

    Person(String name, Date birth, double salary) {
        this.name = name;
        this.birth = birth;
        this. salary = salary;
    }
    static void printPersons(Person[] persons){
        for (Person one : persons) {
            System.out.format(Locale.getDefault(),"|%-10s|%td/%tm/%tY|%,10.2f|", one.name, one.birth, one.birth, one.birth, one.salary);    //%2$td %2$tB %2$tY    one.birth,
            System.out.println();
//            System.out.println(one.name + "|"+one.birth + "|"+one.salary);
        }
    }
    public static void main(String[] args) {
        Person[] persons = new Person[1];
        persons[0] = new Person("Саша", new Date(System.currentTimeMillis()), 10000);
        printPersons(persons);
    }

    @Override
    public String toString() {
        return "|%10s|%2$td %2$tB %2$tY|%3d";
    }
}
//  |Вася      |01/01/1970|200 000,00|
//   1234567890