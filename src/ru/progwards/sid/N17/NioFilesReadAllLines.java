package ru.progwards.sid.N17;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

//  25 - Чтение из файла всех строк
public class NioFilesReadAllLines {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/Projects/Academy/Java1/file1.txt");
        List<String> allLines = Files.readAllLines(path);
        for (String line : allLines)
            System.out.println("-> " + line);
    }
}
