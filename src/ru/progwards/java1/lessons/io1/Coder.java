package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//  Задача 2. Класс Coder
public class Coder {
    //  прочитать файл inFileName и перекодировать его посимвольно в соответствии с заданным шифром
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName){
        try {
            FileReader reader = new FileReader(inFileName);
            FileWriter writer = new FileWriter(outFileName);
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    String strFromFile = scanner.nextLine();
                    char[] symbol = strFromFile.toCharArray();
                    for (int i = 0; i < symbol.length; i++){
//                        System.out.println(symbol[i] + "|"+(int)symbol[i]);
                        symbol[i] = code[(int)symbol[i]];
                    }
                    String strToFile = new String(symbol);  //String.valueOf(symbol);   //symbol.toString();
                    writer.write(strToFile);  //   + "\n"
                }
            } finally {
                reader.close();
                writer.close();
            }
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            log(e.getMessage(), logName);
        }
    }
    public static boolean log(String msg, String logName) {
        try {
            FileWriter logFile = new FileWriter(logName, true);
            try {
                logFile.write(msg + "\n");
            } finally {
                logFile.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        char[] code = {'a', 'b', 'c'};
        codeFile(
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file1.txt",
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file3.txt",
                code,
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file4.txt");
    }
}
