package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ClassInspector {
    public static void inspect(String clazz) throws ClassNotFoundException {
        //  getSimpleName - проверить, где можно использовать
        //  Хорстман, 254
        // вывести имя класса и суперкласса
        Class cl = Class.forName(clazz);
        System.out.println(cl);     //  class ru.progwards.java2.lessons.reflection.Employee
        System.out.println(cl.getName());   //  ru.progwards.java2.lessons.reflection.Employee
        System.out.println(cl.getSimpleName()); //  Employee
        Class supercl = cl.getSuperclass(); //  class java.lang.Object
        System.out.println(supercl);
        String modifiers = Modifier.toString(cl.getModifiers());    //  1/public
        System.out.println(cl.getModifiers() + "/" +modifiers);

        printConstructors(cl);
    }
    //  Выводит все конструкторы класса
        public static void printConstructors(Class cl) {
            System.out.println("Выводит все конструкторы класса");
            Constructor[] constructors = cl.getDeclaredConstructors();
            for (Constructor const1 : constructors) {
                String name = const1.getName();
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
                        System.out.print(paramTypes[j].getName() + " " + parameters[j].getName());
                }
                System.out.println(");");
            }

    //  https://www.concretepage.com/java/jdk-8/java-8-reflection-access-to-parameter-names-of-method-and-constructor-with-maven-gradle-and-eclipse-using-parameters-compiler-argument
//            System.out.println("Выводит имена параметров конструкторов класса");
//            Constructor<?>[] constructors = cl.getDeclaredConstructors();
//            for (Constructor<?> constructor : constructors) {
//                System.out.println(constructor.getName());
//                System.out.println("-------------");
//                Parameter[] parameters = constructor.getParameters();
//                for (Parameter p : parameters) {
////  https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Parameter.html
////                    if (p.isNamePresent()) {
//                        System.out.println(p.getName());
////                    }
//                }
//            }
        }
    public static void main(String[] args) throws ClassNotFoundException {
        inspect("ru.progwards.java2.lessons.reflection.Employee");
//        inspect("Employee");

    }
}
//      public Employee(){
//      public Employee(String name, int age) {
//      public Employee(String name, int age, int salary) {

//  Добрый день, Александр!
//Class<String> clz = String.class;
//for (Method m : clz.getDeclaredMethods()) {
//   System.err.println(m.getName());
//   for (Parameter p : m.getParameters()) {
//      System.err.println("  " + p.getName());
//   }
//}
//Но здесь есть тонкость. Параметр компилятору. Если он компилирует с параметрами по умолчанию, обычно имена он будет давать такие: arg1, arg2 и т.д.
//А если указать параметр "-parameters", то будут правильные имена.