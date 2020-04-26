package ru.progwards.sid.N18;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class SimpleExcelReaderExample {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        String excelFilePath = "C:\\Users\\sidnet1964\\Documents\\Book1.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
//                System.out.println(cell.getCellType());
//                Cell celldata=(Cell) cellitr.next();
                switch(cell.getCellType()){
//                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                    case FORMULA:
                        System.out.print(cell.getCachedFormulaResultType());    //  тип результата
                        System.out.print(" - ");
                        System.out.print(cell.getCellFormula());    //  формула
                        System.out.print(" - ");
                        System.out.print(cell.getStringCellValue());    //  результат вычисления
                        break;
                }
                System.out.print(" - ");
            }
            System.out.println();
        }

        workbook.close();
        inputStream.close();
    }

}
