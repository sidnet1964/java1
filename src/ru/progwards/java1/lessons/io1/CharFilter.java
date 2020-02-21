package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter){
        try {
            FileReader reader = new FileReader(inFileName);
            FileWriter writer = new FileWriter(outFileName);
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
//                    String outputText = "";
                    String strFromFile = scanner.nextLine();
                    for (int i = 0; i<filter.length(); i++){
                        strFromFile = strFromFile.replace(filter.substring(i, i+1), "");
                    }
//                    System.out.println(strFromFile);
                    writer.write(strFromFile + "\n");
                }
            } finally {
                reader.close();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filter = " -,.()";
        filterFile(
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file1.txt",
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file2.txt",
                filter
        );
    }
}
