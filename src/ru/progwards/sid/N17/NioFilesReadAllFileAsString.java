package ru.progwards.sid.N17;

import java.io.IOException;
import java.nio.file.*;

public class NioFilesReadAllFileAsString {
    static boolean replaceF(String name){
        Path path = Paths.get(name);
        try {
            String fileAsString = Files.readString(path);
            String nfileAsString = fileAsString.replace('F', 'f');
            Files.writeString(path, nfileAsString);
        } catch (IOException e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(replaceF("C:/Projects/Academy/Java1/file2.txt"));
    }
}
