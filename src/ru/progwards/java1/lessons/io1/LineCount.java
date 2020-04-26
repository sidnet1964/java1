package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//  Задача 1. Класс LineCount
public class LineCount {
    //  посчитать количество пустых строк в файле
    public static int calcEmpty(String fileName){
        int count = 0;
        try {
            FileReader reader = new FileReader(fileName);
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    String strFromFile = scanner.nextLine();
                    if (strFromFile.length() == 0)
                        count++;
                }
            }
            finally {
                reader.close();
            }
        } catch (IOException e) {
//            throw new IOException("файл не найден");
            return -1;
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(calcEmpty("C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file1.txt"));
    }
}
