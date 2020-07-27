package ru.progwards.sid.A19;

import java.io.*;

//  https://habr.com/ru/post/431524/
class Home implements Serializable {
    private String home;

    public Home(String home) {
        this.home = home;
    }
    public String getHome() {
        return home;
    }
}

public class Person implements Serializable {
    private static final long serialVersionUID = System.currentTimeMillis();
    private String name;
    private int countOfNiva;
    private String fatherName;
    private Home home;

    public Person(String name, int countOfNiva, String fatherName, Home home) {
        this.name = name;
        this.countOfNiva = countOfNiva;
        this.fatherName = fatherName;
        this.home = home;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", countOfNiva=" + countOfNiva +
                ", fatherName='" + fatherName + '\'' +
                ", home=" + home +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Home home = new Home("Vishnevaia 1");
        Person igor = new Person("Igor", 2, "Raphael", home);
        Person renat = new Person("Renat", 2, "Raphael", home);

        //Сериализация в файл с помощью класса ObjectOutputStream
        //  Для сериализации объектов в поток используется класс ObjectOutputStream.
        //  https://metanit.com/java/tutorial/6.10.php
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("person.out"));
        //  Класс FileOutputStream предназначен для записи байтов в файл. Он является производным
        //  от класса OutputStream, поэтому наследует всю его функциональность.
        //   https://metanit.com/java/tutorial/6.3.php
        objectOutputStream.writeObject(igor);
        objectOutputStream.writeObject(renat);
        //  void writeObject(Object obj): записывает в поток отдельный объект
        objectOutputStream.close();

        // Востановление из файла с помощью класса ObjectInputStream
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("person.out"));
        //  Для считывания данных из файла предназначен класс FileInputStream, который является
        //  наследником класса InputStream и поэтому реализует все его методы.
        Person igorRestored = (Person) objectInputStream.readObject();
        Person renatRestored = (Person) objectInputStream.readObject();
        objectInputStream.close();

        //Сериализация с помощью класса ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream2.writeObject(igor);
        objectOutputStream2.writeObject(renat);
        objectOutputStream2.flush();

        //Восстановление с помощью класса ByteArrayInputStream
        ObjectInputStream objectInputStream2 = new ObjectInputStream(
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Person igorRestoredFromByte = (Person) objectInputStream2.readObject();
        Person renatRestoredFromByte = (Person) objectInputStream2.readObject();
        objectInputStream2.close();

        System.out.println("Before Serialize: " + "\n" + igor + "\n" + renat);
        System.out.println("After Restored  : " + "\n" + igorRestored + "\n" + renatRestored);
        System.out.println("After From Byte : " + "\n" + igorRestoredFromByte + "\n" + renatRestoredFromByte);
        System.out.println(serialVersionUID);

    }
}