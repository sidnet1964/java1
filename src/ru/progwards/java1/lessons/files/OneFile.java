package ru.progwards.java1.lessons.files;

//  по условию задания этот класс не требуется, но он нужен для хранения информации о файле
//  интерфейс Comparable необходим для сортировки полученного списка файлов
//  если файлы являются дубликатами, то они будут стоять рядом. Ввиду найденного способа
//  группировки, механизм можно изменить при освоении многоуровневой группировки
public class OneFile implements Comparable<OneFile>{
    public String name;
    public String path;
    public String last; //  lastModifiedTime    2020-04-11T21:18:52.6062482Z
    public String size; //  размер в строку     40
    public String line; //  первая строка       isDirectory: false

//  конструктор
    public OneFile(String name, String path, String last, String size, String line) {
        this.name = name;
        this.path = path;
        this.last = last;
        this.size = size;
        this.line = line;
    }

    @Override
    public String toString() {
        return "OneFile{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
//                ", last='" + last + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public int compareTo(OneFile file) {
        String tKey = this.name+this.last+this.size;
        String oKey = file.name+file.last+file.size;
        return tKey.compareTo(oKey);
    }
}
