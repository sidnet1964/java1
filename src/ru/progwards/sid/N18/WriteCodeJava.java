package ru.progwards.sid.N18;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteCodeJava {
//  создать новую книгу (без имени)
    private static Workbook getWorkbook() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        return workbook;
    }
////  создать новый лист в книге
//    private static Sheet setSheet(Workbook workbook, String nameSheet) throws IOException {
//        Sheet newSheet = workbook.createSheet (nameSheet);
//        return newSheet;
//    }

    //  Для сохранения изменений можно применить следующий метод:
    public static void writeWorkbook(Workbook wb, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
        }
        catch (Exception e) {
            //Обработка ошибки
        }
    }

    public static void main(String[] args) throws IOException {
//        String excelFilePath = "C:\\Users\\sidnet1964\\Documents\\Book1.xlsx"; // can be .xls or .xlsx
//        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = getWorkbook();
        Sheet sheet1 = workbook.createSheet("List1");
//        workbook.createSheet("List2");
//  вывод списка листов
//        int numberOfSheets = workbook.getNumberOfSheets();
//        for (int i = 0; i < numberOfSheets; i++) {
//            Sheet aSheet = workbook.getSheetAt(i);
//            System.out.println(aSheet.getSheetName());
//        }
        Row row1 = sheet1.createRow(1);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue(1.1);
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue(2.2);
        writeWorkbook(workbook, "C:\\Users\\sidnet1964\\Documents\\Book2.xlsx");

    }
}
