package ru.progwards.sid.N17;

import java.io.IOException;
import java.nio.file.*;

//  24 - Чтение из файла всех байт
public class NioFilesReadAllBytes {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/Projects/Academy/Java1/file1.txt");
        byte[] allBytes = Files.readAllBytes(path);
        for (int i = 0, j = allBytes.length; i < j; i++) {
            if (i % 10 == 0)
                System.out.println();
            int unsignedByte = allBytes[i] & 0xFF;
            System.out.print(Integer.toHexString(unsignedByte) + ",");
        }
    }
}
