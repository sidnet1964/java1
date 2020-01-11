package ru.progwards.java1.lessons.classes2;

public class HashCode {
    public static void main(String[] args) {
        System.out.println("Хешкоды от new Object():");
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
        System.out.println("Хешкоды от new Integer(5):");
        System.out.println(new Integer(5).hashCode());
        System.out.println(new Integer(5).hashCode());
        System.out.println(new Integer(5).hashCode());
        System.out.println("Хешкоды от new String(\"Строка\"):");
        System.out.println(new String("Строка").hashCode());
        System.out.println(new String("Строка").hashCode());
        System.out.println(new String("Строка").hashCode());
//        System.out.println(new Object().hashCode());    //  189568618
//        System.out.println(new Object().hashCode());    //  793589513
    }

}

//    Хешкоды от new Object():
//        189568618
//        793589513
//        1313922862
//        Хешкоды от new Integer(5):
//        5
//        5
//        5
//        Хешкоды от new String("Строка"):
//        1236370293
//        1236370293
//        1236370293
