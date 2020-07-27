package ru.progwards.sid.A19;

import java.io.*;

//  https://habr.com/ru/post/431524/
//  Сериализация с использованием transient
public class Logon implements Serializable {
    private String login;
    private transient String password;

    public Logon(String login, String password) {
        this.login = login;
        this.password = password;
    }


    @Override
    public String toString() {
//        return "Logon{" +
//                "login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                '}';
        return String.format("Logon{login='%s', password='%s'}", login, password);
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Logon igor = new Logon("IgorIvanovich", "Khoziain");
        Logon renat = new Logon("Renat", "2500RUB");
        System.out.println("Before: \n" + igor);
        System.out.println(renat);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Externals.out"));
        out.writeObject(igor);
        out.writeObject(renat);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Externals.out"));
        igor = (Logon) in.readObject();
        renat = (Logon) in.readObject();

        System.out.println("After: \n" + igor);
        System.out.println(renat);
    }
}