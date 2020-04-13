package ru.progwards.sid.N17;

import java.io.IOException;
import java.nio.file.*;

//  14 - Создание каталога и файла
public class NioFilesCreateFileDir {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/Projects/Academy/Java1");
        Path newDir = path.resolve("tmp_dir");
        System.out.println("Создаём каталог: " + newDir);
        Files.createDirectory(newDir);
        Path newFile = newDir.resolve("tmp_file.txt");
        System.out.println("Создаём файл: " + newFile);
        Files.createFile(newFile);
    }
}
