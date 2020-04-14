package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilesSelect {
    public static void selectFiles(String inFolder, String outFolder, List<String> keys) throws IOException {
        List<Path> fList;
        Path dir2 = Paths.get(outFolder);   //  имя каталога назначения
        try {
            fList = createList(inFolder);    //  получить список всех файлов с атрибутами
            Iterator<Path> it1 = fList.iterator();
            while (it1.hasNext()) {
                Path itObj = it1.next();                    //  файл для копирования
                String text = extractCont(itObj);
                Iterator<String> it2 = keys.iterator();
                while (it2.hasNext()) {
                    String itStr = it2.next();              //  имя подкаталога назначения
                    if (text.contains(itStr)){
//                        System.out.println(itStr + " -> " +itObj);
                        //  найти или создать папку в outFolder
                        Path dstDir = dir2.resolve(itStr);
                        if (!Files.exists(dstDir)) {
                            // действия, если папка не существует
                            Files.createDirectory(dstDir);  //  создать каталог
                        }
                        Path dstFile = dstDir.resolve(itObj.getFileName().toString());
                        Files.copy(itObj, dstFile, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        } catch (IOException e) {
            throw e;
        }
//        System.out.println(fList);
    }
    //  ---------------------------------------------
    //  перебор всех файлов и формирование списка
    public static List<Path> createList(String startPath) throws IOException {
        final String pattern = "glob:**/*.txt";
        List<Path> fList = new ArrayList<>();
        if (startPath == null)
            return fList;
        try {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) {
//                        String last = Files.getAttribute(path, "basic:lastModifiedTime").toString();
//                        String size = Files.getAttribute(path, "basic:size").toString();
                        fList.add(path);
//                        String text = extractCont(path);
//                        if (compareCont(text, ))
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

//        fList.sort(null);
        return fList;
    }
//  --------------------------------------------------
//  извлечь содержимое файла и проверить
static String extractCont(Path path) throws IOException {
    String fileAsString = "";
    try {
        fileAsString = Files.readString(path);
    } catch (IOException e){
        throw e;
    }
    return fileAsString;
}

    //  ==========================================================
    public static void main(String[] args) throws IOException {
        List<String> keys = new ArrayList<>();
        keys.add("Alpha");
        keys.add("Beta");
        keys.add("Gamma");
        keys.add("Delta");
        keys.add("Epsilon");
        selectFiles("C:/Projects/Academy/Java2", "C:/Projects/Academy/Java1", keys);   //  "C:/Projects/Academy/Java1"
    }
}
//Α, α Alpha ['ælfə] - альфа
//Β, β Beta ['bi:tə] - бета
//Γ, γ Gamma ['gæmə] - гамма
//Δ, δ Delta ['deltə] - дельта
//Ε, ε Epsilon ['epsəֽlɔn], BrE [ep'saɪlən] - эпсилон