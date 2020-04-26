package ru.progwards.sid.N18;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;

//  https://habr.com/ru/post/56817/
public class Excel {
    //  Для чтения книги из файла можно применить следующий код:
    public static HSSFWorkbook readWorkbook(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            return wb;
        }
        catch (Exception e) {
            return null;
        }
    }
    //  Для сохранения изменений можно применить следующий метод:
    public static void writeWorkbook(HSSFWorkbook wb, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
        }
        catch (Exception e) {
            //Обработка ошибки
        }
    }
    public static void main(String[] args) {
//        Workbook wb = new HSSFWorkbook();
        System.out.println(System.getProperty("java.class.path")); //  .getProperties()
        HSSFWorkbook wb = readWorkbook("C:\\Users\\sidnet1964\\Documents\\Book1.xls");

        writeWorkbook(wb, "C:\\Users\\sidnet1964\\Documents\\Book2.xls");
    }
}
//  C:\Users\sidnet1964\Documents
//  Книга1.xlsx
