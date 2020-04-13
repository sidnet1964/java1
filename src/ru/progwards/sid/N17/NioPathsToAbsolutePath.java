package ru.progwards.sid.N17;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NioPathsToAbsolutePath {
    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("C:/Projects/Academy/Java1");
        Path path2 = Paths.get("../Java1");
        Path path3 = Paths.get("");
        Path path4 = Paths.get(".");
        Path path5 = Paths.get("..");
        System.out.println("path1: " + path1.toAbsolutePath());
        System.out.println("path2: " + path2.toAbsolutePath());
        System.out.println("path3: " + path3.toAbsolutePath());
        System.out.println("path4: " + path4.toAbsolutePath());
        System.out.println("path5: " + path5.toAbsolutePath());
    }
}
