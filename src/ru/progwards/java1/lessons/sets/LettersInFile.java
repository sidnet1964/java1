package ru.progwards.java1.lessons.sets;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.TreeSet;

public class LettersInFile {
    public static String process(String fileName) throws IOException {
        String ret = "";
        TreeSet<Character> set1 = new TreeSet<>();  // множество для собирания символов
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            String currentLine = raf.readLine();
            if (currentLine != null){
                currentLine = new String(currentLine.getBytes("ISO-8859-1"), "UTF-8");
//                System.out.println(currentLine);
            }
            char[] alphabet = currentLine.toCharArray();
            for (char letter : alphabet){
                if (Character.isLetter(letter))
                    set1.add(letter);   //  добавить символ в коллекцию
            }
            Character[] array = set1.toArray(new Character[0]);
            for (Character letter : array)
                ret += letter + "";

        } catch (Exception e) {
            throw e;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(process("C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\sets\\file1.txt"));
//        System.out.println(process(null));

    }
}
