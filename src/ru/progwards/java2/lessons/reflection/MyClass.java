package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

public class MyClass {
    private int number;
    private String name = "default";
    //    public MyClass(int number, String name) {
//        this.number = number;
//        this.name = name;
//    }
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
        System.out.println(number + name);
    }
//  ------------------------------------
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        int number = myClass.getNumber();
        String name0 = null; //no getter =(
        String name1 = null; //no getter =(
        System.out.println(number + " " + name0);//output 0null
        try {
            //  Field[] fields = myClass.getClass().getDeclaredFields();
            //  System.out.println(Arrays.toString(fields));
            //  [private int ru.progwards.java2.lessons.reflection.MyClass.number, private java.lang.String ru.progwards.java2.lessons.reflection.MyClass.name]
            Field field = myClass.getClass().getDeclaredField("name");
            //  System.out.println(myClass.getClass());
            //  class ru.progwards.java2.lessons.reflection.MyClass
            //  System.out.println(field);
            //  private java.lang.String ru.progwards.java2.lessons.reflection.MyClass.name
            field.setAccessible(true);
            name0 = (String) field.get(myClass);
            field.set(myClass, (String) "new value");
            name1 = (String) field.get(myClass);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(number + " " + name0);//output 0 default
        System.out.println(number + " " + name1);//output 0 new value
    }
}
