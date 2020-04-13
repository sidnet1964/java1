package ru.progwards.sid.N17;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NioPathsGet {
    public static void main(String[] args) {
        Path path1 = Paths.get("C:\\Users\\sidnet1964\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\sid\\file1.txt");
//        Path path2 = Paths.get("move");
//        Path path3 = Paths.get("file1.txt");

//        System.out.println("path1:");
//        System.out.println(path1);  //  C:\Users\sidnet1964\IdeaProjects\HelloWorld\src\ru\progwards\sid\file1.txt
//        System.out.println(path1.toAbsolutePath());     // -"-
        //  Возвращает вызывающий объект типа Path в виде абсолютного пути

//        System.out.println("\npath2:");
//        System.out.println(path2);                      //  src
//        System.out.println(path2.toAbsolutePath());     //  C:\Users\sidnet1964\IdeaProjects\HelloWorld\src
//
//        System.out.println("\npath3:");
//        System.out.println(path3);  //  file1.txt
//        System.out.println(path3.toAbsolutePath()); //  C:\Users\sidnet1964\IdeaProjects\HelloWorld\file1.txt

        System. out.println( "path1: " + path1);
//  Возвращает имя файла, связанное с вызывающим объектом типа Path
        System. out.println( "getFileName(): " + path1.getFileName());      //  file1.txt
//  Количество элементов (кроме корневого) в вызывающем объекте типа Path
        System. out.println( "getNameCount: " + path1.getNameCount());      //  getNameCount: 9
//  Возвращает объект типа Path, содержащий имя элемента пути по указанному индежсу в вызывающем объекте.
//  Крайний слева элемент имеет нулевой индекс и находится ближе всего к корневому каталогу.
//  А крайний справа элемент имеет индекс getNameCount() - 1
        System. out.println( "getName(0): " + path1.getName( 0));       //  getName(0): Users
        System. out.println( "getName(1): " + path1.getName( 7));       //  getName(7): sid
        System. out.println( "getName(2): " + path1.getName( 8));       //  getName(8): file1.txt
        System. out.println( "subpath(0, 1): " + path1.subpath( 0, 1)); //  subpath(0, 1): Users
        System. out.println( "subpath(1, 3): " + path1.subpath( 5, 8)); //  subpath(5, 8): ru\progwards\sid
//  Возвращает корневой каталог из вызывающего объекта типа Path
        System. out.println( "getRoot: " + path1.getRoot());        //  getRoot: C:\
//  Возвращает объект типа Path, который содержит весь путь, кроме имени файла,
//  определяемого вызывающим объектом типа Path
        System. out.println( "getParent: " + path1.getParent());
//  C:\Users\sidnet1964\IdeaProjects\HelloWorld\src\ru\progwards\sid
    }
}
