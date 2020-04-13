package ru.progwards.sid.N17;

import java.io.IOException;
import java.nio.file.*;

//  4 - Класс Files: информация о файле или каталоге
public class NioCheckFilesAndDirs {
    public static void main(String[] args) throws IOException {
        Path pathName = Paths.get("C:\\Users\\sidnet1964\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\sid\\N17\\file1.txt");
        System.out.println("exists: " + Files.exists(pathName));
        System.out.println("notExists: " + Files.notExists(pathName));
        System.out.println("isReadable: " + Files.isReadable(pathName));
        System.out.println("isWritable: " + Files.isWritable(pathName));
        System.out.println("isDirectory: " + Files.isDirectory(pathName));
        System.out.println("isRegularFile: " + Files.isRegularFile(pathName));
        System.out.println("isHidden: " + Files.isHidden(pathName));
        System.out.println("isExecutable: " + Files.isExecutable(pathName));
    }}
