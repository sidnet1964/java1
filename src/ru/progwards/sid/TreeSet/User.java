package ru.progwards.sid.TreeSet;

import java.util.Comparator;
import java.util.TreeSet;

public class User {
    public Integer id;
    public String name;
    User (Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public TreeSet<User> createSet(){
        TreeSet<User> treeSet = new TreeSet<>( new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(-o1.id, -o2.id);
            }
        });
    return treeSet;
    }

    public static void main(String[] args) {

    }
}
