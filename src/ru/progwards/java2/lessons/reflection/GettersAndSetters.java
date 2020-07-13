package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

//  Реализовать метод public static check(String) который анализирует,
//  для каких private полей класса нет сеттера или геттера и выводит
//  на консоль сигнатуры отсутствующих методов;
public class GettersAndSetters {
    public static void check(String clazz) throws ClassNotFoundException {
        Class cl = Class.forName(clazz);
        List<Metods> fieldsList = fillFields(cl);
        List<Metods> metodsList = printMethods(cl);
        for (Metods line1 : fieldsList) {
            boolean flag = false;
            for (Metods line2 : metodsList) {
                if (line1.toString().compareTo(line2.toString()) == 0){
                    flag = true;
                    break;
                }
            }
            if (!flag)
                System.out.println(line1.totoString());
        }
    }
    //  ------------------------------------
    //  посторить список всех возможных методов
    static List<Metods> fillFields(Class cl) {
        List<Metods> fieldsArrayList = new ArrayList<>();    //  список всех возможных методов
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers == "private") {
                fieldsArrayList.add(new Metods("public", type.getSimpleName(), "get" + firstUpperCase(name), "", 0));
                fieldsArrayList.add(new Metods("public", "void", "set" + firstUpperCase(name), type.getSimpleName(), 1));
            }
        }
        return fieldsArrayList;
    }
    //  ------------------------------------
    //  построить список существующих методов
    public static List<Metods> printMethods(Class cl) {
        List<Metods> metodsArrayList = new ArrayList<>();    //  список всех существующих методов
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();
            // вывести модификаторы, возвращаемый тип и имя метода
            String modifiers = Modifier.toString(m.getModifiers());
            // вывести типы параметров
//            Parameter[] parameters = m.getParameters();
            Class[] paramTypes = m.getParameterTypes();
            String paramList = "";
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0)
                    paramList += ", ";
                paramList += paramTypes[j].getSimpleName();
//                System.out.print(paramTypes[j].getSimpleName() + " " + parameters[j].getName());
            }
            metodsArrayList.add(new Metods(modifiers, retType.getSimpleName(), name, paramList, paramTypes.length));
        }
        return metodsArrayList;
    }
    //  ------------------------------------
    static String firstUpperCase(String word){
        if(word == null || word.isEmpty())
            return ""; //или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    //  ====================================
    public static void main(String[] args) throws ClassNotFoundException {
        check("ru.progwards.java2.lessons.reflection.Person");
    }
}
