package ru.progwards.sid;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

public class doEx {
    public static void doExceptions(int n) throws Throwable {
        Throwable suppressed = null;
        try {
            System.out.println("doExceptions 1");
            if (n == 0)
                throw new Exception("Exception in try");
            System.out.println("doExceptions 2");
        } catch (Throwable t) {
            suppressed = t;
            throw t;
        } finally {
            try {
                throw new Exception("Exception in finally");
            } catch (Throwable t) {
                if (suppressed != null)
                    t.addSuppressed(suppressed);
                throw t;                }
        }
    }
//    и функция, его вызывающая:
    public void doSomething(int n) throws IOException{

    }
    public void test(int n) throws IOException {
        try {
            doSomething(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            System.out.println("finally executed");
        }
    }
    public static void scanLines(){
        Boolean bCont = true;
        int choice = 0;
        try(Scanner scanner = new Scanner(System.in)) {
            while (bCont) {
                choice = 0;
                String str = scanner.nextLine();
                if (str.contains("/stop"))
                    choice += 1;
                if (str.contains("Привет"))
                    choice += 10;
                if (str.contains("как дела"))
                    choice += 100;
                switch (choice){
                   case 1:
                      bCont = false;    break;
                   case 10:
                       System.out.println("Здравствуйте!");  break;
                    case 100:
                        System.out.println("Хорошо");    break;
                   case 110:
                       System.out.println("Здравствуйте");
                       System.out.println("Хорошо");
                       break;
                   default:
                       System.out.println(str);
                    }
            }
        }
    }
    static String invertWords(String sentence){
        String[] regNumArr = sentence.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : regNumArr) {
            stringBuilder.insert(0, word);  //   .append(word);
            stringBuilder.insert(0, ".");  //   .append(word);
        }
        stringBuilder.deleteCharAt(0);
        return String.valueOf(stringBuilder);
    }
    public static String setStars(String filename) throws IOException, FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        try(RandomAccessFile raf = new RandomAccessFile(filename, "rw");) {
//            String currentLine = raf.readLine();
//            if (currentLine == null)
//                return "";
//            currentLine = new String(currentLine.getBytes("ISO-8859-1"), "UTF-8");
//            System.out.println(currentLine);
//            char[] buk = currentLine.toCharArray();
//            System.out.println(Arrays.toString(buk));
            for (int i = 0; i < raf.length(); i++){
                if (i % 10 == 9)
                {
                    raf.seek((long)i);
                    byte sym = raf.readByte();
//                    System.out.println(i + " | " + (char)sym);
                    raf.seek((long)i);
                    raf.write('*');
                    stringBuilder.append((char)sym);
                }
            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getClass());
        } catch (IOException e) {
//            System.out.println(e.getClass());
        }
        return String.valueOf(stringBuilder);
    }
    public static void main(String[] args) throws IOException, FileNotFoundException {
        System.out.println(setStars("C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\sid\\несуществующий путь/несуществующий файл"));
//        System.out.println(invertWords("Буря мглою небо кроет"));
//        scanLines();
//        try {
//            doExceptions(0);
//        } catch (Throwable e) {
//            System.out.println(e.getMessage());
//            for (Throwable t: e.getSuppressed())
//                System.out.println(t.getMessage() + " (suppressed)");
//        }
    }
}
