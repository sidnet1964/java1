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
    public static List<List<String>> findDuplicates(String startPath) throws IOException {
        List<List<String>> myList = new ArrayList<>();
        List<OneFile> fList = createList(startPath);    //  получить список всех файлов с атрибутами
        OneFile groupObj = null;
        boolean first = true;   //  указатель первого элемента
        List<String> list1 = null;
        int index = 0;
        Iterator<OneFile> iterator = fList.iterator();
        while (iterator.hasNext()) {
            index++;
            OneFile itObj = iterator.next();
            System.out.println(index + " - значение = " + itObj);
            if (first) {
                groupObj = itObj; //  запомнить первый элемент
                first = false;  //  больше этого не повтрится
                list1 = new ArrayList<>();  //  пустой список
                list1.add(groupObj.path + "\\" + groupObj.name);
            }
            else {
                if (itObj.compareTo(groupObj) == 0) { //  если ключи равны - проверить содержимое
                    if (compareCont(itObj, groupObj))
                        list1.add(itObj.path + "\\" + itObj.name);
                }
                else {
                        System.out.println(index + " - list1 = " + list1);
                        if (list1.size() > 1)
                            myList.add(list1);
                        groupObj = itObj; //  запомнить новый элемент
                        list1 = new ArrayList<>();
                        list1.add(groupObj.path + "\\" + groupObj.name);
                    }
            }
        }
        System.out.println(index + " - list1 = " + list1);
        if (list1 == null)
            return myList;

        if (list1.size() > 1)
            myList.add(list1);

        return myList;
    }
    //  ---------------------------------------------
    //  сравнить содержимое двух файлов
    static boolean compareCont(OneFile itObj, OneFile groupObj) throws IOException {
        String text1 = extractCont(itObj);
        String text2 = extractCont(groupObj);
        return text1.equals(text2);
    }
    //  ---------------------------------------------
    //  сравнить содержимое двух файлов
    static String extractCont(OneFile xObj) throws IOException {
        String fileAsString = "";
        try {
            Path path = Paths.get(xObj.path+"\\"+xObj.name);
            fileAsString = Files.readString(path);
        } catch (IOException e){
            throw e;
        }
        return fileAsString;
    }

    //  ---------------------------------------------
    //  перебор всех файлов и формирование списка
    public static List<OneFile> createList(String startPath) throws IOException {
        final String pattern = "glob:**/*";
        List<OneFile> fList = new ArrayList<>();
        if (startPath == null)
            return fList;
        try {

            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) {
                        String last = Files.getAttribute(path, "basic:lastModifiedTime").toString();
                        String size = Files.getAttribute(path, "basic:size").toString();
                        fList.add(new OneFile(path.getFileName().toString(), path.getParent().toString(), last, size, null));
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e){
            throw e;
        }

        fList.sort(null);
        return fList;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(findDuplicates("C:/Projects/Academy/Java2"));   //  "C:/Projects/Academy/Java1"
    }
}
