package ru.progwards.java1.lessons.files;

public class OneFile implements Comparable<OneFile>{
//  lastModifiedTime: 2020-04-11T21:18:52.6062482Z
//  size: 40
//  isDirectory: false
    public String name;
    public String path;
    public String last; //  lastModifiedTime
    public String size; //  размер в строку
    public String line; //  первая строка

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
                ", last='" + last + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public int compareTo(OneFile file) {
        String tKey = this.name+this.last+this.size;
        String oKey = file.name+file.last+file.size;
        return tKey.compareTo(oKey);
//        return 0;
    }
}
