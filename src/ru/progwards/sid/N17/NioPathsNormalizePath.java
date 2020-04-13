package ru.progwards.sid.N17;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NioPathsNormalizePath {
//  Создайте метод с сигнатурой String createFolder(String name), который создает каталог name
//  (один уровень) в текущей папке и возвращает полный путь родителя текущего каталога
    static String createFolder(String name){
        Path path = Paths.get(name);
        return path.toAbsolutePath().getParent().getParent().toString();
    }
        public static void main(String[] args) {
//            Path path = Paths. get("..");
//            System. out.println("path: " + path);   //  path: ..
//
//            Path absPath = path.toAbsolutePath();
//            System. out.println("\nabsPath: " + absPath);   //  absPath: C:\Users\sidnet1964\IdeaProjects\HelloWorld\..
//
//            Path normPath = absPath.normalize();            //  normPath: C:\Users\sidnet1964\IdeaProjects
//            System. out.println("\nnormPath: " + normPath);
            System.out.println(createFolder("sidnet"));
        }
}
