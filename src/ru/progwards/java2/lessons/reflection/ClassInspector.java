package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Modifier;

public class ClassInspector {
    public static void inspect(String clazz) throws ClassNotFoundException {
        //  Хорстман, 254
        // вывести имя класса и суперкласса
        Class cl = Class.forName(clazz);    //  class ru.progwards.java2.lessons.reflection.Employee
        System.out.println(cl);
        Class supercl = cl.getSuperclass(); //  class java.lang.Object
        System.out.println(supercl);
        String modifiers = Modifier.toString(cl.getModifiers());    //  public
        System.out.println(modifiers);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        inspect("ru.progwards.java2.lessons.reflection.Employee");

    }
}
