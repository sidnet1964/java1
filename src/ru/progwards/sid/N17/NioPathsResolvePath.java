package ru.progwards.sid.N17;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NioPathsResolvePath {
        public static void main(String[] args) {
//            Path path = Paths.get("C:/Projects/Academy/Java1");
            Path path  = Paths.get("C:\\Users\\sidnet1964\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\sid\\");
// Если указанный путь является абсолютным, возвращается именно этот путь.
// А если указанный путь не содержит корневой каталог, то этот путь предваряется корневым каталогом
// из вызывающего объекта типа Path, а затем возвращается полученный результат.
// Если же указанный путь пуст, то возвращается вызывающий объект типа Path.
// В противном случае поведение данного метода не определено
            Path path1 = path.resolve("src");
            Path path2 = path.resolve("/Projects/Academy");
            Path path3 = path.resolve("..");
            System.out.println("path: " + path + "\n");
            System.out.println("path1: " + path1);
            System.out.println("path2: " + path2);
            System.out.println("path3: " + path3);
        }
}
