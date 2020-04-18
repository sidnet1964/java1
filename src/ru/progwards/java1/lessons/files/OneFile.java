package ru.progwards.java1.lessons.files;

import java.nio.file.Path;

//  по условию задания этот класс не требуется, но он нужен для хранения информации о файле
//  интерфейс Comparable необходим для сортировки полученного списка файлов
//  если файлы являются дубликатами, то они будут стоять рядом. Ввиду найденного способа
//  группировки, механизм можно изменить при освоении многоуровневой группировки
public class OneFile implements Comparable<OneFile>{
//public class OneFile implements Comparable<OneFile>{
    public Path filename;
    public String last; //  lastModifiedTime    2020-04-11T21:18:52.6062482Z
    public String size; //  размер в строку     40
    public String line; //  первая строка       isDirectory: false

//  конструктор
public OneFile(Path filename, String last, String size, String line) {
    this.filename = filename;
        this.last = last;
        this.size = size;
        this.line = line;
    }

    @Override
    public String toString() {
        return "OneFile{" +
                "filename=" + filename +
                ", last='" + last + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public int compareTo(OneFile file) {
        String tKey = this.filename.getFileName() + this.last + this.size;
        String oKey = file.filename.getFileName() + file.last + file.size;
        return tKey.compareTo(oKey);
    }
}
