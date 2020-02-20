package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) throws IOException {
        int count = 0;
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String strFromFile = scanner.nextLine();
                if (strFromFile.length() == 0)
                    count ++;
            }
            reader.close();
        } catch (IOException e) {
//            throw new IOException("файл не найден");
            return -1;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(calcEmpty("C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file10.txt"));
    }
}
