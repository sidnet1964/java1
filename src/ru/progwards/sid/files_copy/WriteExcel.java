package ru.progwards.sid.files_copy;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//  запись коллекции в таблицу Excel
public class WriteExcel {
    public Workbook workbook;
//  конструктор по умолчанию
    WriteExcel(){
    //  создать новую книгу (без имени)
        workbook = new XSSFWorkbook();
    }

    //  Для сохранения изменений можно применить следующий метод:
    public static void writeWorkbook(Workbook wb, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
            System.out.println("Записан файл " + fileName);
        }
        catch (Exception e) {
            System.out.println("Ошибка записи " + fileName);
            //Обработка ошибки
        }
    }

    public void writeSheet(List<?> names){
        System.out.println(names.getClass().getName());
        System.out.println(names.getClass().getComponentType());
        Sheet sheet1 = workbook.createSheet("List1"); //  создать лист
        Row row1 = sheet1.createRow(0);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue(1.1);
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue(2.2);
        writeWorkbook(workbook, "C:\\Users\\sidnet1964\\Documents\\Book2.xlsx");
    }
    public static void main(String[] args) throws IOException {
        WriteExcel book1 = new WriteExcel();    //  новая книга (объект)
        Sheet sheet1 = book1.workbook.createSheet("List1"); //  создать лист
        writeWorkbook(book1.workbook, "C:\\Users\\sidnet1964\\Documents\\Book2.xlsx");
    }
}
