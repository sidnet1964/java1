package ru.progwards.java2.lessons.reflection;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Person {
    private String name;

//    Person(String name) {
//        this.name = name;
//    }
//    private void setName(String name) {
//        this.name = name;
//    }

    public Person() {
        name = "no name";
    }

    private Person(String name) {
        this.name = name;
    }

    static Person callConstructor(String name){
        Class clazz = Person.class;
        Class[] params = {String.class};
        Person myClass = null;
        try {
            myClass = (Person) clazz.getDeclaredConstructor(params).newInstance(name);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return myClass;
    }

//    static void setName(Person person, String name){
//        try {
//            Field field = person.getClass().getDeclaredField("name");
//            field.setAccessible(true);
//            field.set(person, (String) name);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

//    static void callSetName(Person person, String name) {
//        for (Method method : person.getClass().getDeclaredMethods()) {
//            if (method.getName() == "setName") {
//                method.setAccessible(true);
//                try {
//                    method.invoke(person, name);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

        public static void main(String[] args) {
//        Person vip = new Person("Вася");
//        setName(vip, "Петя");
//        callSetName(vip, "Петя");
            Person vip = Person.callConstructor("Миша");
        System.out.println(vip.name);
    }
}
