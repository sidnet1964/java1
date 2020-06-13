package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

//  https://javarush.ru/groups/posts/513-reflection-api-refleksija-temnaja-storona-java
//  Reflection API. Рефлексия. Темная сторона Java
public class MyClass {
    private int number;
    private String name = "default";
        public MyClass(int number, String name) {
        this.number = number;
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setName(String name) {
        this.name = name;
    }
    private void printData(){
        System.out.println(number + " / " + name);
    }
    //  ------------------------------------
    //  для вызова объекта Method используем invoke(Оbject, Args),
    //  где Оbject — все также экземпляр класса MyClass.
    //  Args — аргументы метода — наш таковых не имеет.
    public static void printData(Object myClass){
        try {
            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    //  ------------------------------------
    public static void main(String[] args) {
//        MyClass myClass = new MyClass();
//        int number = myClass.getNumber();
//        String name0 = null; //no getter =(
//        String name1 = null; //no getter =(
//        printData(myClass); // outout 0default
//        System.out.println(number + " " + name0);//output 0null
//        try {
//            //  Field[] fields = myClass.getClass().getDeclaredFields();
//            //  System.out.println(Arrays.toString(fields));
//            //  [private int ru.progwards.java2.lessons.reflection.MyClass.number, private java.lang.String ru.progwards.java2.lessons.reflection.MyClass.name]
//            Field field = myClass.getClass().getDeclaredField("name");
//            //  System.out.println(myClass.getClass());
//            //  class ru.progwards.java2.lessons.reflection.MyClass
//            //  System.out.println(field);
//            //  private java.lang.String ru.progwards.java2.lessons.reflection.MyClass.name
//            field.setAccessible(true);
//            name0 = (String) field.get(myClass);
//            field.set(myClass, (String) "new value");
//            name1 = (String) field.get(myClass);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        System.out.println(number + " " + name0);//output 0 default
//        System.out.println(number + " " + name1);//output 0 new value

//        MyClass myClass = null;
//        try {
//            Class clazz = Class.forName(MyClass.class.getName());
//            myClass = (MyClass) clazz.newInstance();
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        System.out.println(myClass);//ru.progwards.java2.lessons.reflection.MyClass@b1bc7ed

        //  Перепишем создание экземпляра класса:
        MyClass myClass = null;
        Class clazz = null;
        try {
            clazz = Class.forName(MyClass.class.getName());
            //  MyClass.class.getName() = "ru.progwards.java2.lessons.reflection.MyClass"
            //  clazz = "class ru.progwards.java2.lessons.reflection.MyClass"
            Class[] params = {int.class, String.class};
            myClass = (MyClass) clazz.getConstructor(params).newInstance(2, "default2");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(myClass);//output created object reflection.MyClass@60e53b93

        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class paramType : paramTypes) {
                System.out.print(paramType.getName() + " == ");
            }
            System.out.println();
        }
        printData(myClass); // outout 0default

    }
}
