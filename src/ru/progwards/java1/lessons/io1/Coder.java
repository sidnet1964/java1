package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName){
    //  прочитать файл inFileName и перекодировать его посимвольно в соответствии с заданным шифром,
    //  результат записать в outFileName. Шифр задается маccивом char[] code,
    //  где каждому символу symbol оригинального файла соответствует символ
    //  code[(int)symbol] выходного файла. В случае ошибок, в файл с именем logName
    //  вывести название ошибки через метод класса Exception - getMessage()
        try {
            FileReader reader = new FileReader(inFileName);
            FileWriter writer = new FileWriter(outFileName);
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    String strFromFile = scanner.nextLine();
                    for (int i = 0; i<code.length; i++){
//                        strFromFile = strFromFile.replace(code[i], "");
                        System.out.println(code[i]+"|"+code[(int)code[i]]);
                    }
                    writer.write(strFromFile);  //   + "\n"
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
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file2.txt",
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file3.txt",
                code,
                "C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\io1\\file4.txt");
    }
}
