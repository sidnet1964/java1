package ru.progwards.sid.test;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Try {
    static public Integer sqr(Integer n){
        try {
            Integer rezult = n * n;
            return rezult;
        }
        catch (NullPointerException npe) {
            return -1;
        }
    }
//    проверяет filename и если он равен null выбрасывает IOException со строкой "File not found",
//    в противном случает возвращает строку "File processing"
    public static String test(String filename) throws IOException {
            try {
                String fn = filename.toString();
            }
            catch (Exception e){
                 throw new IOException("File not found");
                }
            return "File processing";
    }
    public static void test() throws IOException {
        throw new IOException();
    }
    private static int lineCount(String filename) throws IOException {
        int count = 0;
        try {
            FileReader reader = new FileReader(filename);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String strFromFile = scanner.nextLine();
                count ++;
            }
            reader.close();
        } catch (Exception e) {
            throw new IOException("файл не найден");
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
//        System.out.println(sqr(null));
//        System.out.println(test(null));
        System.out.println(lineCount("C:\\Users\\sidne\\IdeaProjects\\HelloWorld\\src\\ru\\progwards\\sid\\test\\file1.txt"));
    }
}
