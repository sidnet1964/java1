package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//  В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию
//  (и расширению), дате-времени последнего изменения, размеру и по содержимому.
public class FindDuplicates {
    //  конструктор по умолчанию
    public FindDuplicates() {
    }

    //  НЕ конструктор
    public static List<List<String>> findDuplicates(String startPath) {
        List<OneFile> fileList;     //  список всех файлов с атрибутами
        List<List<String>> myList = new ArrayList<>();
        fileList = createList(startPath);    //  получить

        OneFile itObj;              //  текущий элемент
        OneFile groupObj = null;    //  первый объект для группировки
        boolean first = true;       //  указатель первого элемента
        List<String> list1 = null;  //  список
        int index = 0;
        for (OneFile oneFile : fileList) {
            index++;
            itObj = oneFile;
//            System.out.println(index + " - значение = " + itObj);
            if (first) {
                groupObj = itObj; //  запомнить первый элемент
                first = false;  //  больше этого не повтрится
                list1 = new ArrayList<>();  //  пустой список
                list1.add(Paths.get(startPath).relativize(groupObj.filename).toString());
            } else {
                if (itObj.compareTo(groupObj) == 0) { //  если ключи равны - проверить содержимое
                    if (compareCont(itObj, groupObj))
                        list1.add(Paths.get(startPath).relativize(itObj.filename).toString());
                } else {
                    if (list1.size() > 1)
                        myList.add(list1);
                    groupObj = itObj; //  запомнить новый элемент
                    list1 = new ArrayList<>();
                    list1.add(Paths.get(startPath).relativize(groupObj.filename).toString());
                }
            }
        }
        if (list1 == null)
            return myList;

        if (list1.size() > 1)
            myList.add(list1);

        return myList;
    }
    //  ---------------------------------------------
    //  сравнить содержимое двух файлов
    static boolean compareCont(OneFile itObj, OneFile groupObj) {
        String text1 = extractCont(itObj);
        String text2 = extractCont(groupObj);
        return text1.equals(text2);
    }
    //  ---------------------------------------------
    //  извлечь содержимое файла
    static String extractCont(OneFile xObj) {
        String fileAsString = "";
        try {
            Path path = Paths.get(xObj.filename.toString());    /// ПРОВЕРИТЬ
            fileAsString = Files.readString(path);
        } catch (IOException e){
            System.out.println(e);
        }
        return fileAsString;
    }

    //  ---------------------------------------------
    //  перебор всех файлов и формирование списка
    public static List<OneFile> createList(String startPath) {
        final String pattern = "glob:**/*";
        List<OneFile> fileList = new ArrayList<>();
        if (startPath == null)
            return fileList;
        try {

            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) {
                        String last = Files.getAttribute(path, "basic:lastModifiedTime").toString();
                        String size = Files.getAttribute(path, "basic:size").toString();
//                        fileList.add(new OneFile(path.getFileName().toString(), path.getParent().toString(), last, size, null));
                        fileList.add(new OneFile(path, last, size, null));
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e){
            System.out.println(e);
        }

        fileList.sort(null);
        return fileList;
    }

    //  в условиях задачи не указано, что findDuplicates - статический метод,
    //  но и структуре класса никаких требовний нет. Пока статический вариант 
    public static void main(String[] args) {
        //  1 - вызов с реально существующим путем
        System.out.println(findDuplicates("C:/Projects/Academy/Java1"));
        //  2 - вызов с НЕ существующим путем
//        System.out.println(findDuplicates("C:/Projects/Academy/Java2"));
        //  3 - вызов с null
//        System.out.println(findDuplicates(null));
    }
}
