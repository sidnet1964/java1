package ru.progwards.sid.N17;

import java.io.IOException;
import java.nio.file.*;

//  7 - Копирование файла в java.nio
public class NioFileCopy {
    final static String HOME_DIR = "C:/Projects/Academy/Java1";
    public static void main(String[] args) {
        Path dir1 = Paths.get(HOME_DIR);
        try {
            Path srcFile = dir1.resolve("file1.txt");       //  оригинал
            Path dstFile = dir1.resolve("file1copy.txt");   //  копия
            Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING);
//  10 - Копирование каталога в java.nio
            Path srcDir = dir1.resolve("idea");
            Path dstDir = dir1.resolve("idea_copy");
            Files.copy(srcDir, dstDir, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
