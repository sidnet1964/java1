package ru.progwards.sid.N17;

import java.io.IOException;
import java.nio.file.*;

//  19 - -Получить basic атрибут
public class NioFilesGetAttribute {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/Projects/Academy/Java1/file1.txt");
        String[] attrNames = {
                "lastModifiedTime",
                "lastAccessTime",
                "creationTime",
                "size",
                "isRegularFile",
                "isDirectory",
                "isSymbolicLink",
                "isOther" // что-то другое
        };
        System.out.println("Базовый атрибут:");
        for (String attrName: attrNames)
            System.out.println(attrName + ": " +
                    Files.getAttribute(path, "basic:" + attrName));
    }
}
