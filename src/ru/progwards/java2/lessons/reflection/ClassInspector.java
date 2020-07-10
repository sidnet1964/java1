package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.*;

public class ClassInspector {
    public static void inspect(String clazz) throws ClassNotFoundException {
        //  getSimpleName - проверить, где можно использовать
        //  Хорстман, 254
        // вывести имя класса и суперкласса
        Class cl = Class.forName(clazz);
        String modifiers = Modifier.toString(cl.getModifiers());    //  1/public
        System.out.println(modifiers + " class " + cl.getSimpleName() + " {"); //  Employee

        printConstructors(cl);
        System.out.println();
        printFields(cl);
        System.out.println();
        printMethods(cl);
        System.out.println("}");
    }
    //  ------------------------------------
    //  Выводит все конструкторы класса
    public static void printConstructors(Class cl) {
//            System.out.println("Выводит все конструкторы класса");
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor const1 : constructors) {
            //  использовать cl.getSimpleName() в качестве имени конструктора
            String name = cl.getSimpleName();   //  const1.getName();
            System.out.print(" ");
            String modifiers = Modifier.toString(const1.getModifiers());
            if (modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.print(name + "(");
            // вывести типы параметров
            Parameter[] parameters = const1.getParameters();
            Class[] paramTypes = const1.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getSimpleName() + " " + parameters[j].getName());
            }
            System.out.println("){}");
        }
    }
//  ------------------------------------
    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            System.out.print(" ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.println(type.getSimpleName() + " " + name + ";");
        }
    }
    //  ------------------------------------
    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();
            System.out.print(" ");
// вывести модификаторы, возвращаемый тип и имя метода
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.print(retType.getSimpleName() + " " + name + "(");
// вывести типы параметров
            Parameter[] parameters = m.getParameters();
            Class[] paramTypes = m.getParameterTypes();
            for (int j =0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                    System.out.print(paramTypes[j].getSimpleName() + " " + parameters[j].getName());
            }
            System.out.println(") {}");
            }
        }
//  ====================================
    public static void main(String[] args) throws ClassNotFoundException {
        inspect("ru.progwards.java2.lessons.reflection.Employee");
    }
}
